package dev.leandro.kotlin_spring_boot.serivces


import dev.leandro.kotlin_spring_boot.exceptions.ResourceNotFoundException
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

    fun findAll(): List<Person> {
        logger.info("Finding all people!")
        return repository.findAll()
    }

    fun findById(id: Long): Person {
        logger.info("Finding one person!")
        return repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
    }

    fun create(person: Person) : Person{
        logger.info("Creating one person with name ${person.firstName}!")
        return repository.save(person)
    }

    fun update(person: Person) : Person{
        logger.info("Updating one person with ID ${person.id}!")
        val entity = findById(person.id)

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.email = person.email
        entity.address = person.address
        entity.gender = person.gender
        return repository.save(entity)
    }

    fun delete(id: Long) {
        logger.info("Deleting one person with ID $id!")
        val entity = findById(id)
        repository.delete(entity)
    }
}