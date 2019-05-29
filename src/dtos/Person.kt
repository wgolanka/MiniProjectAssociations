package dtos

import java.lang.IllegalStateException

class Person(val name : String) {

    private var teaRatings: MutableSet<PersonTeaRating> = mutableSetOf()

    fun addTeaRating(personTeaRating: PersonTeaRating) {
        if(personTeaRating.getPerson() != this) {
            throw IllegalStateException("This rating belongs to different Person!")
        }
        teaRatings.add(personTeaRating)

    }

    fun getTeaRatings(): MutableSet<PersonTeaRating> {
        return teaRatings
    }

    override fun toString(): String {
        return "Person(name='$name', teaRatings=$teaRatings)"
    }

    fun removeRating(personTeaRating: PersonTeaRating) { // test what happens when no rating
        teaRatings.remove(personTeaRating)
    }
}