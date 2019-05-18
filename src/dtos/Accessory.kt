package dtos

class Accessory(val id: Int) {

    private var company: Company? = null

    fun setCompany(companyToSet: Company){
        if(company == null){
            company = companyToSet
        }else{
            company!!.removeAccessory(this)
            company = null
            companyToSet.addAccessory(this)
        }
    }

    fun getCompany(): Company? {
        return company
    }

    override fun toString(): String {
        return "Accessory(id=$id, company=${company?.name})"
    }
}