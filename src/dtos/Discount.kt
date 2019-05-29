package dtos

import java.lang.Exception

class Discount(val code : String) {

    private var employeesQualifier = mutableMapOf<Int, Employee>()

    fun addEmployee(employee: Employee){
        if(!employeesQualifier.containsKey(employee.id)){
            employeesQualifier[employee.id] = employee

            employee.addDiscount(this)
        }
    }

    fun findEmployeeQualifier(id : Int) : Employee{
        if(!employeesQualifier.containsKey(id)){
            throw Exception("Unable to find employee $id")
        }
        return employeesQualifier[id]!!
    }

    override fun toString(): String {
        return "Discount(code='$code', " +
                " employeesQualifier=$employeesQualifier)"
    }
}