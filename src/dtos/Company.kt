package dtos

class Company(val name : String) {

    private var accessories: MutableList<Accessory> = mutableListOf()

    fun addAccessory(accessory: Accessory){
        if(!accessories.contains(accessory)){
            accessories.add(accessory)

            accessory.setCompany(this)
        }
    }

    fun getAccessories(): List<Accessory> {
        return accessories
    }

    fun removeAccessory(accessory: Accessory) {
        accessories.remove(accessory)
    }

    override fun toString(): String {
        return "Company(name='$name', accessories=$accessories)"
    }
}