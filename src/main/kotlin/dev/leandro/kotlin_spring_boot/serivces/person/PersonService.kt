package dev.leandro.kotlin_spring_boot.serivces.person


import dev.leandro.kotlin_spring_boot.data.vo.v1.PersonVO
import dev.leandro.kotlin_spring_boot.exceptions.ResourceNotFoundException
import dev.leandro.kotlin_spring_boot.mapper.ModelMapper
import dev.leandro.kotlin_spring_boot.model.Person
import dev.leandro.kotlin_spring_boot.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<PersonVO> {
        logger.info("Finding all people!")
        val persons = repository.findAll()
        return ModelMapper.parseListObjects(persons, PersonVO::class.java)
    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding one person!")
        var person = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        return ModelMapper.parseObject(person, PersonVO::class.java)
    }

    fun create(person: PersonVO) : PersonVO{
        logger.info("Creating one person with name ${person.firstName}!")
        var entity: Person = ModelMapper.parseObject(person, Person::class.java)
        return ModelMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun update(person: PersonVO) : PersonVO{
        logger.info("Updating one person with ID ${person.id}!")
        val entity = repository.findById(person.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.email = person.email
        entity.address = person.address
        entity.gender = person.gender
        return ModelMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun delete(id: Long) {
        logger.info("Deleting one person with ID $id!")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        repository.delete(entity)
    }
}