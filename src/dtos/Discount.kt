package dtos

import java.lang.Exception

class Discount(val code : String) {

    private var employees  = mutableListOf<Employee>()

    private var employeesQualifier = mutableMapOf<Int, Employee>()

    fun addEmployee(employee: Employee){
        if(!employees.contains(employee)){
            employees.add(employee)
        }
    }

    fun addEmployeeQualifier(employee: Employee){
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
                "employees=${employees.joinToString { "id: "  + it.id.toString() + " and name: " + it.name }}," +
                " employeesQualifier=$employeesQualifier)"
    }
}