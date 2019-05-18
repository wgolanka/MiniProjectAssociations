package tests

import dtos.*
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertFails


internal class AssociationsTests{

    @Test
    fun shouldBeOrdinaryAssociation(){
        //given
        val company = Company("Wiki Software")
        println("Given $company")

        //when
        val brewer = Accessory(1234)
        val cup = Accessory(5678)
        println("\nWhen create two objects $brewer and $cup")

        company.addAccessory(cup)
        company.addAccessory(brewer)

        //then
        println("\nAfter all objects are created and accessory is added to company")
        println("$company\n$brewer\n$cup")

        assertTrue(brewer.getCompany() == company)
        assertTrue(cup.getCompany() == company)
        assertTrue(company.getAccessories().containsAll(listOf(brewer, cup)))

        println("\nAfter accessory already have a company:\n")
        val anotherCompany = Company("Funny Software")
        println(anotherCompany)
        println()

        anotherCompany.addAccessory(brewer)

        println("$anotherCompany\n$brewer\n$cup\n" +
                "$company")

        assertTrue(brewer.getCompany() == anotherCompany)
        assertTrue(cup.getCompany() == company)
    }

    @Test
    fun shouldBeAssociationWithAttribute(){
        //given when
        val person = Person("Jack")
        val tea = Tea("Green")
        val personTeaRating = PersonTeaRating(5, LocalDate.now(), person, tea)

        println("\n$person \n$tea \n$personTeaRating")

        //then
        assertTrue(person.getTeaRatings().contains(personTeaRating))
        assertTrue(tea.getRatings().contains(personTeaRating))

        println("\nOverride rating")
        val anotherPerson = Person("Susie")
        val anotherTea = Tea("Black")

        personTeaRating.overrideRatingRelation(anotherPerson, anotherTea)

        println("\n$person \n$tea \n$personTeaRating \n$anotherPerson \n$anotherTea")

        assertFalse(person.getTeaRatings().contains(personTeaRating))
        assertFalse(tea.getRatings().contains(personTeaRating))
        assertTrue(anotherPerson.getTeaRatings().contains(personTeaRating))
        assertTrue(anotherTea.getRatings().contains(personTeaRating))
    }

    @Test
    fun shouldBeAssociationWithQualifier(){
        //given
        val jackEmp = Employee("Jack", 123)
        val susieEmp = Employee("Susie", 456)

        val summerDiscount = Discount("SUMMER")
        val annualDiscount = Discount("ANNUAL")
        println("Created objects: \n$jackEmp\n$susieEmp\n$summerDiscount\n$annualDiscount")

        //when
        summerDiscount.addEmployeeQualifier(jackEmp)

        annualDiscount.addEmployeeQualifier(jackEmp)
        annualDiscount.addEmployeeQualifier(susieEmp)

        //then
        println("\nAfter adding adding objects: \n$jackEmp\n$susieEmp\n$summerDiscount\n$annualDiscount")

        val employee = annualDiscount.findEmployeeQualifier(123)
        println("\nEmployee found by id 123 = $employee")
        assertTrue(employee == jackEmp)

    }

    @Test
    fun shouldAssociationWithQualifierThrowExceptionWhenNoQualifier(){
        //given
        val annualDiscount = Discount("ANNUAL")

        //when then
        assertFails { annualDiscount.findEmployeeQualifier(123) }
    }

    @Test
    fun shouldBeAssociationWithComposition(){
        //given
        val teaGreen = Tea("Green")
        val teaBlack = Tea("Black")

        println(teaGreen)
        println(teaBlack)

        //when
        BrewMethod.create("Green tea method", teaGreen)
        val brewingMethod = BrewMethod.create("Another tea method", teaGreen)

        //then
        assertTrue(teaGreen.getBrewMethods().contains(brewingMethod))

        println("\nAfter creating parts")
        println(teaGreen)
        println(teaBlack)

        assertFails {teaBlack.addBrewMethod(brewingMethod)}
      //  teaBlack.addBrewMethod(brewingMethod) // throws exception
    }
}