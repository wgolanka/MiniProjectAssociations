package dtos

import java.lang.Exception

class BrewMethod private constructor(var name : String, var tea: Tea){
    companion object {
        fun create(brewMethodName: String, brewMethodTea: Tea?): BrewMethod {
            if(brewMethodTea == null){
                throw Exception("Given tea doesn't exist")
            }

            val brewMethod = BrewMethod(brewMethodName, brewMethodTea)
            brewMethodTea.addBrewMethod(brewMethod)
            return brewMethod
        }
    }

    override fun toString(): String {
        return "BrewMethod(name='$name', tea=${tea.name})"
    }
}