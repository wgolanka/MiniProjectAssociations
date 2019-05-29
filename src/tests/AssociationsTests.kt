package tests

import dtos.*
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertFails


internal class AssociationsTests {

    @Test
    fun shouldBeOrdinaryAssociationCompanySide() { // Company 1 - * Accessory
        //given
        val company = Company("Wiki Software")
        println("Given $company")
        val brewer = Accessory(1234)
        val cup = Accessory(5678)

        //when
        company.addAccessory(cup)
        company.addAccessory(brewer)
        brewer.setCompany(company)

        //then
        println("\nAfter all objects are created and accessory is added to company")
        println("$company\n$brewer\n$cup")

        assertTrue(brewer.getCompany() == company)
        assertTrue(cup.getCompany() == company)
        assertTrue(company.getAccessories().containsAll(listOf(brewer, cup)))
    }

    @Test
    fun shouldBeOrdinaryAssociationCompanySideOverride() {
        val coffeeCompany = Company("Coffee Shop")
        val brewer = Accessory(1234)
        val teaCompany = Company("Tea shop")
        coffeeCompany.addAccessory(brewer)
        println("Given:\n$coffeeCompany\n$teaCompany\n$brewer\n")

        //when
        println("\nWhen: \nadding accessory to Tea Shop company which already has Coffee Shop company assigned\n")
        teaCompany.addAccessory(brewer)

        assertTrue(brewer.getCompany() == teaCompany)
        assertTrue(teaCompany.getAccessories().contains(brewer))
        assertFalse(coffeeCompany.getAccessories().contains(brewer))
        println("Then:\n$coffeeCompany\n$teaCompany\n$brewer\n")
    }

    @Test
    fun shouldBeOrdinaryAssociationAccessorySide() {
        //given
        val company = Company("Wiki Software")
        val brewer = Accessory(1234)
        println("Given $company \n and : $brewer")

        //when
        brewer.setCompany(company)

        //then
        assertTrue(company.getAccessories().contains(brewer))
        assertTrue(brewer.getCompany() == company)
    }

    @Test
    fun shouldBeOrdinaryAssociationAccessorySideOverride() {
        //given
        val coffeeCompany = Company("Coffee Shop")
        val teaCompany = Company("Tea shop")
        val brewer = Accessory(1234)
        println("Given $coffeeCompany \n and : $brewer")
        brewer.setCompany(coffeeCompany)

        //when
        println("\n$coffeeCompany\n$teaCompany\n$brewer\n")
        brewer.setCompany(teaCompany)

        //then
        assertFalse(coffeeCompany.getAccessories().contains(brewer))
        assertFalse(brewer.getCompany() == coffeeCompany)
        assertTrue(teaCompany.getAccessories().contains(brewer))
        assertTrue(brewer.getCompany() == teaCompany)
        println("After setting new company for brewer")
        println("\n$coffeeCompany\n$teaCompany\n$brewer")
    }

    @Test
    fun shouldBeAssociationWithAttribute() { // Person * - * Tea
        //given
        val person = Person("Jack")
        val tea = Tea("Green")

        //when
        val personTeaRating = PersonTeaRating(5, LocalDate.now(), person, tea)

        println("\n$person \n$tea \n$personTeaRating")

        //then
        assertTrue(person.getTeaRatings().contains(personTeaRating))
        assertTrue(tea.getRatings().contains(personTeaRating))
        assertTrue(personTeaRating.getPerson() == person)
        assertTrue(personTeaRating.getTea() == tea)
//        PersonTeaRating(3, LocalDate.now(), null, tea)  //can't pass null

    }

    @Test
    fun shouldBeAssociationWithAttributeCantOverride() {
        //given
        val person = Person("Jack")
        val tea = Tea("Green")
        val anotherPerson = Person("Arnold")
        val anotherTea = Tea("Black")
        val personTeaRating = PersonTeaRating(5, LocalDate.now(), person, tea)
        println("\n$person \n$tea \n$personTeaRating")

        //when then
        assertFails { anotherPerson.addTeaRating(personTeaRating) }
        assertFails { anotherTea.addRating(personTeaRating) }
        println("\n$person \n$tea \n$personTeaRating")
    }

    @Test
    fun shouldRemoveAssociationWithAttribute(){
        //given
        val person = Person("Jack")
        val tea = Tea("Green")
        val personTeaRating = PersonTeaRating(5, LocalDate.now(), person, tea)
        println("\n$person \n$tea \n$personTeaRating")

        //when
        personTeaRating.deleteRating()
        println("\n$person \n$tea \n$personTeaRating")

        //then
        assertFalse(person.getTeaRatings().contains(personTeaRating))
        assertFalse(tea.getRatings().contains(personTeaRating))
        assertFalse(personTeaRating.getPerson() == person)
        assertFalse(personTeaRating.getTea() == tea)
    }

    @Test
    fun shouldBeAssociationWithQualifierDiscountSide() { // Employee * - * Discount
        //given
        val jackEmp = Employee("Jack", 123)
        val susieEmp = Employee("Susie", 456)

        val summerDiscount = Discount("SUMMER")
        val annualDiscount = Discount("ANNUAL")
        println("Created objects: \n$jackEmp\n$susieEmp\n$summerDiscount\n$annualDiscount")

        //when
        summerDiscount.addEmployee(jackEmp)
        annualDiscount.addEmployee(jackEmp)
        annualDiscount.addEmployee(susieEmp)

        //then
        println("\nAfter adding adding objects: \n$jackEmp\n$susieEmp\n$summerDiscount\n$annualDiscount")

        val employee = annualDiscount.findEmployeeQualifier(123)
        println("\nEmployee found by id 123 = $employee")

        assertTrue(jackEmp.getDiscounts().containsAll(setOf(summerDiscount, annualDiscount)))
        assertTrue(susieEmp.getDiscounts().contains(annualDiscount))
        assertTrue(summerDiscount.findEmployeeQualifier(123) == jackEmp)
        assertTrue(annualDiscount.findEmployeeQualifier(123) == jackEmp)
        assertTrue(annualDiscount.findEmployeeQualifier(456) == susieEmp)
        assertFails { summerDiscount.findEmployeeQualifier(456) }
    }

    @Test
    fun shouldBeAssociationWithQualifierEmpSide() {
        //given
        val jackEmp = Employee("Jack", 123)
        val susieEmp = Employee("Susie", 456)
        val summerDiscount = Discount("SUMMER")
        val annualDiscount = Discount("ANNUAL")
        println("Created objects: \n$jackEmp\n$susieEmp\n$summerDiscount\n$annualDiscount")

        //when
        jackEmp.addDiscount(summerDiscount)
        jackEmp.addDiscount(annualDiscount)
        susieEmp.addDiscount(annualDiscount)

        //then
        assertTrue(jackEmp.getDiscounts().containsAll(setOf(summerDiscount, annualDiscount)))
        assertTrue(susieEmp.getDiscounts().contains(annualDiscount))
        assertTrue(summerDiscount.findEmployeeQualifier(123) == jackEmp)
        assertTrue(annualDiscount.findEmployeeQualifier(123) == jackEmp)
        assertTrue(annualDiscount.findEmployeeQualifier(456) == susieEmp)
        assertFails { summerDiscount.findEmployeeQualifier(456) }
        println("\nAfter all set: \n$jackEmp\n$susieEmp\n$summerDiscount\n$annualDiscount")

    }

        @Test
    fun shouldAssociationWithQualifierThrowExceptionWhenNoQualifier() {
        //given
        val annualDiscount = Discount("ANNUAL")

        //when then
        assertFails { annualDiscount.findEmployeeQualifier(189) }
    }

    @Test
    fun shouldBeAssociationWithComposition() {
        //given
        val teaGreen = Tea("Green")
        val teaBlack = Tea("Black")

        println(teaGreen)
        println(teaBlack)

        //when
        val brewingMethod = BrewMethod.create("Some tea method", teaGreen)
        val brewingMethod2 = BrewMethod.create("Japan tea method", teaGreen)
        val brewingMethod3 = BrewMethod.create("Strong tea method", teaBlack)

        //then
        assertTrue(teaGreen.getBrewMethods().contains(brewingMethod))

        println("\nAfter creating parts")
        println(teaGreen)
        println(teaBlack)

        assertFails {
            teaBlack.addBrewMethod(brewingMethod)
        }
        //  teaBlack.addBrewMethod(brewingMethod) // throws exception

        println("\nRemove : $brewingMethod")
        Tea.delete(teaGreen)

        println(teaGreen)
        println(brewingMethod)
        println(brewingMethod2)
        println(brewingMethod3)

    }
}