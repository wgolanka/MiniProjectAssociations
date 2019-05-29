package dtos

class Accessory(val id: Int) {

    private var company: Company? = null

    fun setCompany(newCompany: Company){
        if(company == newCompany){
            return
        }
        if(company != null){
            company!!.removeAccessory(this)
        }
        company = newCompany
        newCompany.addAccessory(this)
    }

    fun getCompany(): Company? {
        return company
    }

    override fun toString(): String {
        return "Accessory(id=$id, company=${company?.name})"
    }
}