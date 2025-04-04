package dev.leandro.kotlin_spring_boot.serivces.person


import dev.leandro.kotlin_spring_boot.controller.v2.PersonV2Controller
import dev.leandro.kotlin_spring_boot.data.vo.v2.PersonVO
import dev.leandro.kotlin_spring_boot.exceptions.RequiredObjectIsNullException
import dev.leandro.kotlin_spring_boot.exceptions.ResourceNotFoundException
import dev.leandro.kotlin_spring_boot.mapper.custom.PersonMapper
import dev.leandro.kotlin_spring_boot.model.Person
import dev.leandro.kotlin_spring_boot.repository.PersonRepository
import dev.leandro.kotlin_spring_boot.util.DateHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonV2Service {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonV2Service::class.java.name)

    fun findAll(): List<PersonVO> {
        logger.info("Finding all people!")
        val persons = repository.findAll()
        val vos = PersonMapper.mapEntityListToEntityVo(persons)
        for (person in vos) {
            val withSelfRel = linkTo(PersonV2Controller::class.java).slash(person.id).withSelfRel()
            person.add(withSelfRel)
        }
        return vos
    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding one person!")
        var person = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        val personVO: PersonVO = PersonMapper.mapEntityToVO(person)
        val withSelfRel = linkTo(PersonV2Controller::class.java, personVO).slash(personVO.id).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun create(person: PersonVO?) : PersonVO{
        if (person == null) throw RequiredObjectIsNullException("It is not allowed to persist a null object!")
        logger.info("Creating one person with name ${person.firstName}!")
        var entity: Person = PersonMapper.mapVOToEntity(person)
        val personVO: PersonVO =  PersonMapper.mapEntityToVO(repository.save(entity))
        val withSelfRel = linkTo(PersonV2Controller::class.java).slash(personVO.id).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun update(person: PersonVO?) : PersonVO{
        if (person == null) throw RequiredObjectIsNullException("It is not allowed to persist a null object!")
        logger.info("Updating one person with ID ${person.id}!")
        val entity = repository.findById(person.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.email = person.email
        entity.address = person.address
        entity.gender = person.gender
        entity.birthDay = DateHelper.stringToDate(person.birthDay)
        val personVO: PersonVO = PersonMapper.mapEntityToVO(repository.save(entity))
        val withSelfRel = linkTo(PersonV2Controller::class.java).slash(personVO.id).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun delete(id: Long) {
        logger.info("Deleting one person with ID $id!")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        repository.delete(entity)
    }
}