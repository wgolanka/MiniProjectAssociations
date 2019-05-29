package dtos

import java.lang.Exception
import java.lang.IllegalStateException

class Tea(val name : String) {

    private var ratings: MutableSet<PersonTeaRating> = mutableSetOf()

    fun addRating(personTeaRating: PersonTeaRating) {
        if(personTeaRating.getTea() != this){
            throw IllegalStateException("This rating belongs to different Tea!")
        }
        ratings.add(personTeaRating)
    }

    fun getRatings(): MutableSet<PersonTeaRating> {
        return ratings
    }

    override fun toString(): String {
        return "Tea(name='$name'," +
                " ratings=$ratings"
    }

    private var brewMethods = mutableListOf<BrewMethod>()

    internal companion object {
        private var allBrewMethods : MutableSet<BrewMethod> = mutableSetOf()

        fun delete(tea: Tea){
            allBrewMethods.removeAll(tea.brewMethods)
            tea.brewMethods.forEach { method -> method.tea = null }
            tea.brewMethods = emptyList<BrewMethod>().toMutableList()
        }
    }

    fun addBrewMethod(brewMethod: BrewMethod) {
        if(!brewMethods.contains(brewMethod)){
            if(allBrewMethods.contains(brewMethod)){
                throw Exception("This Brew method is already connected with some Tea")
            }

            brewMethods.add(brewMethod)
            allBrewMethods.add(brewMethod)
        }
    }

    fun getBrewMethods(): List<BrewMethod> {
        return brewMethods
    }

    fun removeRating(personTeaRating: PersonTeaRating) {
        ratings.remove(personTeaRating)
    }
}