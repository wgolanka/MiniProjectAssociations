package dtos

import java.time.LocalDate

data class PersonTeaRating(val rate: Int, val date: LocalDate, val person: Person, val tea: Tea) {

    init {
        person.ratings.add(this)
        tea.peopleRatings.add(this)
    }
    override fun toString(): String {
        return "PersonTeaRating(rate=$rate, date=$date, person=${person.name}, tea=${tea.name})"
    }
}
