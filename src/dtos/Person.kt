package dtos

class Person(val name : String) {

    private var teaRatings: MutableList<PersonTeaRating> = mutableListOf()

    fun addTeaRating(personTeaRating: PersonTeaRating) {
        if(!teaRatings.contains(personTeaRating)){
            teaRatings.add(personTeaRating)
        }
        personTeaRating.setPerson(this)
    }

    fun removeTeaRating(personTeaRating: PersonTeaRating) {
        teaRatings.remove(personTeaRating)
    }

    fun getTeaRatings(): MutableList<PersonTeaRating> {
        return teaRatings
    }

    override fun toString(): String {
        return "Person(name='$name', teaRatings=$teaRatings)"
    }
}