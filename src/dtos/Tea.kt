package dtos

class Tea(val name : String) {
    var peopleRatings: MutableList<PersonTeaRating> = mutableListOf()
    var brewMethods : MutableSet<BrewMethod> = mutableSetOf()

    fun createBrewMethod(methodName: String, brewingTime: Int): BrewMethod {
        val brewMethod = BrewMethod(methodName, brewingTime)
        brewMethods.add(brewMethod)
        return brewMethod
    }

    override fun toString(): String {
        return "Tea(name='$name', peopleRatings=$peopleRatings, brewMethods=$brewMethods)"
    }

    class BrewMethod(val methodName : String, val brewingTime : Int) {

        override fun toString(): String {
            return "BrewMethod(methodName='$methodName', brewingTime=$brewingTime)"
        }
    }
}