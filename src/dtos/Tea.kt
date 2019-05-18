package dtos

import java.lang.Exception

class Tea(val name : String) {
    private var ratings: MutableList<PersonTeaRating> = mutableListOf()

    fun addRating(personTeaRating: PersonTeaRating) {
        if(!ratings.contains(personTeaRating)){
            ratings.add(personTeaRating)
        }
        personTeaRating.setTea(this)
    }

    fun removeRating(personTeaRating: PersonTeaRating) {
        ratings.remove(personTeaRating)
    }

    fun getRatings(): MutableList<PersonTeaRating> {
        return ratings
    }

    private var brewMethods = mutableListOf<BrewMethod>()

    private companion object {
        private var allBrewMethods : MutableSet<BrewMethod> = mutableSetOf()
    }

    fun addBrewMethod(brewMethod: BrewMethod) {
        if(!brewMethods.contains(brewMethod)){
            if(allBrewMethods.contains(brewMethod)){
                throw Exception("This Brew method is already connected with a tea")
            }

            brewMethods.add(brewMethod)
            allBrewMethods.add(brewMethod)
        }
    }

    fun getBrewMethods(): List<BrewMethod> {
        return brewMethods
    }

    override fun toString(): String {
        return "Tea(name='$name'," +
                " ratings=$ratings," +
                " brewMethods=$brewMethods," +
                " allBrewMethods=$allBrewMethods)"
    }

}