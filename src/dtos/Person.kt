package dtos

class Person(val name : String) {

    var ratings: MutableList<PersonTeaRating> = mutableListOf()

    override fun toString(): String {
        return "Person(name='$name', ratings=$ratings)"
    }


}