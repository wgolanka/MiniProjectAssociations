package dtos

import java.lang.IllegalStateException
import java.util.*

class Accessory(val id: Int, val company: Company) {

    private var companyQualifiers : TreeMap<String, Company> = TreeMap()

    init {
        addCompanyQualifier(company)
    }

    private fun addCompanyQualifier(company: Company){
        if(!companyQualifiers.containsKey(company.name)){
            companyQualifiers.put(company.name, company)
        }
        company.accessories.add(this)
    }

    override fun toString(): String {
        return "Accessory(id=$id, company=${company.name})"
    }

    public fun findCompany(qualifier: String): Company {
         if(!companyQualifiers.containsKey(qualifier)){
             throw IllegalStateException("Unable to find company + $qualifier")
         }
        return companyQualifiers[qualifier]!!
    }
}