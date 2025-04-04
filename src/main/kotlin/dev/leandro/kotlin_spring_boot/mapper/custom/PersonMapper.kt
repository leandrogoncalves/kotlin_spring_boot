package dev.leandro.kotlin_spring_boot.mapper.custom

import dev.leandro.kotlin_spring_boot.data.vo.v2.PersonVO
import dev.leandro.kotlin_spring_boot.exceptions.DateConversionException
import dev.leandro.kotlin_spring_boot.model.Person
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Service
class PersonMapper {

    fun mapEntityToVO(person: Person): PersonVO {
        val vo = PersonVO()
        vo.id = person.id
        vo.address = person.address
        vo.email = person.email
        vo.firstName = person.firstName
        vo.lastName = person.lastName
        vo.gender = person.gender
        vo.birthDay = dateToString(person.birthDay)
        return vo
    }

    fun mapVOToEntity(person: PersonVO): Person {
        val entity = Person()
        entity.id = person.id
        entity.address = person.address
        entity.email = person.email
        entity.birthDay = stringToDate(person.birthDay)
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
                birthDay = dateToString(it.birthDay)
            ))
        }
        return outputList
    }

    fun stringToDate(dateString: String?): Date? {
        try {
            return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                .parse(dateString)
        } catch (e: Exception) {
            throw DateConversionException("Não foi possível converter a data de nascimento, use o formato dd/mm/YYYY")
        }
    }

    fun dateToString(date: Date?): String {
        return try {
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                .format(date)
        } catch (e: Exception) {
            ""
        }
    }
}