package dev.leandro.kotlin_spring_boot.unit.mapper.mocks

import dev.leandro.kotlin_spring_boot.data.vo.v1.PersonVO
import dev.leandro.kotlin_spring_boot.data.vo.v2.PersonVO as PersonVOv2
import dev.leandro.kotlin_spring_boot.model.Person
import dev.leandro.kotlin_spring_boot.util.DateHelper
import java.util.ArrayList

class MockPerson {
    fun mockEntity(): Person {
        return mockEntity(0)
    }

    fun mockVO(): PersonVO {
        return mockVO(0)
    }

    fun mockEntityList(): ArrayList<Person> {
        val persons: ArrayList<Person> = ArrayList<Person>()
        for (i in 0..13) {
            persons.add(mockEntity(i))
        }
        return persons
    }

    fun mockVOList(): ArrayList<PersonVO> {
        val persons: ArrayList<PersonVO> = ArrayList()
        for (i in 0..13) {
            persons.add(mockVO(i))
        }
        return persons
    }

    fun mockEntity(number: Int): Person {
        val person = Person()
        person.address = "Address Test$number"
        person.firstName = "First Name Test$number"
        person.email = "teste$number@teste.com"
        person.gender = if (number % 2 == 0) "Male" else "Female"
        person.id = number.toLong()
        person.lastName = "Last Name Test$number"
        person.birthDay = DateHelper.stringToDate("16/11/1990")
        return person
    }

    fun mockVO(number: Int): PersonVO {
        val person = PersonVO()
        person.address = "Address Test$number"
        person.firstName = "First Name Test$number"
        person.email = "teste$number@teste.com"
        person.gender = if (number % 2 == 0) "Male" else "Female"
        person.id = number.toLong()
        person.lastName = "Last Name Test$number"
        return person
    }

    fun mockVOv2(number: Int): PersonVOv2 {
        val person = PersonVOv2()
        person.address = "Address Test$number"
        person.firstName = "First Name Test$number"
        person.email = "teste$number@teste.com"
        person.gender = if (number % 2 == 0) "Male" else "Female"
        person.id = number.toLong()
        person.lastName = "Last Name Test$number"
        person.birthDay = "16/11/1990"
        return person
    }
}