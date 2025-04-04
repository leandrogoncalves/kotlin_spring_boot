package dev.leandro.kotlin_spring_boot.mapper.custom

import dev.leandro.kotlin_spring_boot.data.vo.v2.PersonVO
import dev.leandro.kotlin_spring_boot.model.Person
import dev.leandro.kotlin_spring_boot.util.DateHelper
import org.springframework.stereotype.Service

@Service
object PersonMapper {

    fun mapEntityToVO(person: Person): PersonVO {
        val vo = PersonVO()
        vo.id = person.id
        vo.address = person.address
        vo.email = person.email
        vo.firstName = person.firstName
        vo.lastName = person.lastName
        vo.gender = person.gender
        vo.birthDay = DateHelper.dateToString(person.birthDay)
        return vo
    }

    fun mapVOToEntity(person: PersonVO): Person {
        val entity = Person()
        entity.id = person.id
        entity.address = person.address
        entity.email = person.email
        entity.birthDay = DateHelper.stringToDate(person.birthDay)
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.gender = person.gender
        return entity
    }

    fun mapEntityListToEntityVo(personList: List<Person>): ArrayList<PersonVO> {
        val outputList: ArrayList<PersonVO> = ArrayList()
        personList.forEach {
            outputList.add(PersonVO(
                id = it.id,
                firstName = it.firstName,
                lastName = it.lastName,
                address = it.address,
                email = it.email ?: "",
                gender = it.gender,
                birthDay = DateHelper.dateToString(it.birthDay)
            ))
        }
        return outputList
    }

}