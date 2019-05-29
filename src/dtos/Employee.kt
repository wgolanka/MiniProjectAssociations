package dtos

class Employee(val name: String, val id: Int){

    private var discounts = mutableListOf<Discount>()

    fun addDiscount(discount: Discount) {
        if(!discounts.contains(discount)){
            discounts.add(discount)

            discount.addEmployee(this)
        }
    }

    override fun toString(): String {
        return "Employee(name='$name', id=$id, " +
                "discounts=${discounts.joinToString { it.code }}.)"
    }

    fun getDiscounts(): MutableList<Discount> {
        return discounts
    }
}