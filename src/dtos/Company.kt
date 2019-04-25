package dtos

class Company(val name : String) {

    var accessories: MutableList<Accessory> = mutableListOf()

    override fun toString(): String {
        return "Company(name='$name', accessories=$accessories)"
    }
}