package dtos

import java.time.LocalDate

class PersonTeaRating(val rate: Int, val date: LocalDate, personRating: Person, teaRating: Tea) {

    private var person: Person? = null
    private var tea: Tea? = null

    init {
        //no need to check null as null value can not be passed to constructor or method
        setPerson(personRating)
        setTea(teaRating)
    }

    private fun setPerson(personToSet: Person){
        person = personToSet
        personToSet.addTeaRating(this)
    }

    private fun setTea(teaToSet: Tea){
        tea = teaToSet
        teaToSet.addRating(this)
    }

    override fun toString(): String {
        return "PersonTeaRating(rate=$rate, date=$date, person=${person?.name}, tea=${tea?.name})"
    }

    fun getPerson(): Person? {
        return person
    }

    fun getTea(): Tea? {
        return tea
    }

    fun deleteRating() {
        person?.removeRating(this)
        tea?.removeRating(this)
        person = null
        tea = null
    }
}
