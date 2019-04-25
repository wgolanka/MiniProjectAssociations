package tests

import dtos.*
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate


internal class AssociationsTests{

    @Test
    fun shouldBeOrdinaryAssociation(){
        //given
        val company = Company("Software Berg")
        println("Given $company")

        //when
        val brewer = Accessory(1234, company)
        val cup = Accessory(5678, company)
        company.accessories.add(brewer)
        company.accessories.add(cup)

        println("\nWhen create two objects $brewer and $cup")

        //then
        println("\nAfter all objects are created")
        println("$company")
        assertTrue(company.accessories.containsAll(listOf(brewer, cup)))
        assertTrue(brewer.company == company)
        assertTrue(cup.company == company)
    }

    @Test
    fun shouldBeAssociationWithAttribute(){
        //given when
        val person = Person("Jack")
        val tea = Tea("Green")
        val personTeaRating = PersonTeaRating(5, LocalDate.now() , person, tea)
        println("Created objects \n$person \n$tea \n$personTeaRating")

        //then
        assertTrue(person.ratings.contains(personTeaRating))
        assertTrue(tea.peopleRatings.contains(personTeaRating))
        assertTrue(personTeaRating.person == person)
        assertTrue(personTeaRating.tea == tea)
    }

    @Test
    fun shouldBeAssociationWithQualifier(){
        //given
        val companyQualifier = "Paramount"
        val company = Company(companyQualifier)
        val accessory = Accessory(1234, company)
        println("Company object: $company")

        //when
        val companyByQualifier = accessory.findCompany(companyQualifier)
        println("Object from qualifier: $companyByQualifier")

        //then
        assertTrue(company == companyByQualifier)
    }

    @Test
    fun shouldBeAssociationWithComposition(){
        //given
        val tea = Tea("Green")

        //when
        val brewingMethod = tea.createBrewMethod("My Method", 4)

        //then
        assertTrue(brewingMethod == tea.brewMethods.find { method -> method == brewingMethod })
        println(tea)
        println(brewingMethod)
    }
}