package dtos

import java.time.LocalDate

class PersonTeaRating(val rate: Int, val date: LocalDate, personRating: Person, teaRating: Tea) {

    private var person: Person? = personRating
    private var tea: Tea? = teaRating

    init {
        if(person != null && tea != null){
            setPerson(person!!)
            setTea(tea!!)
        }
    }

    fun setPerson(personToSet: Person){
        if(person == null){
            person = personToSet
        }else{
            person!!.removeTeaRating(this)
            person = null
            personToSet.addTeaRating(this)
        }
    }

    fun setTea(teaToSet : Tea){
        if(tea == null){
            tea = teaToSet
        }else{
            tea!!.removeRating(this)
            tea = null
            teaToSet.addRating(this)
        }
    }

    override fun toString(): String {
        return "PersonTeaRating(rate=$rate, date=$date, person=${person?.name}, tea=${tea?.name})"
    }

    fun overrideRatingRelation(person: Person, tea: Tea) {
        person.addTeaRating(this )
        tea.addRating(this)
    }
}
