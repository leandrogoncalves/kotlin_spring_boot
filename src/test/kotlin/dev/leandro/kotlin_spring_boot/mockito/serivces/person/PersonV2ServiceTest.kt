package dev.leandro.kotlin_spring_boot.mockito.serivces.person

import dev.leandro.kotlin_spring_boot.exceptions.RequiredObjectIsNullException
import dev.leandro.kotlin_spring_boot.mapper.custom.PersonMapper
import dev.leandro.kotlin_spring_boot.repository.PersonRepository
import dev.leandro.kotlin_spring_boot.serivces.person.PersonV2Service
import dev.leandro.kotlin_spring_boot.unit.mapper.mocks.MockPerson
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock

import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class PersonV2ServiceTest {

    private lateinit var inputObject: MockPerson

    @InjectMocks
    private lateinit var service: PersonV2Service

    @Mock
    private lateinit var repository: PersonRepository

    @Mock
    private lateinit var mapper: PersonMapper

    @BeforeEach
    fun setUpMock() {
        inputObject = MockPerson()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun findAll() {
        val list = inputObject.mockEntityList()
        println(list)
        `when`(repository.findAll()).thenReturn(list)

        val persons = service.findAll()

        assertNotNull(persons)
        assertEquals(14, persons.size)

        val personOne = persons[1]

        assertNotNull(personOne)
        assertNotNull(personOne.id)
        assertNotNull(personOne.links)
        assertTrue(personOne.links.toString().contains("</v2/person/1>;rel=\"self\""))
        assertEquals("Address Test1", personOne.address)
        assertEquals("First Name Test1", personOne.firstName)
        assertEquals("Last Name Test1", personOne.lastName)
        assertEquals("Female", personOne.gender)

        val personFour = persons[4]

        assertNotNull(personFour)
        assertNotNull(personFour.id)
        assertNotNull(personFour.links)
        assertTrue(personFour.links.toString().contains("</v2/person/4>;rel=\"self\""))
        assertEquals("Address Test4", personFour.address)
        assertEquals("First Name Test4", personFour.firstName)
        assertEquals("Last Name Test4", personFour.lastName)
        assertEquals("Male", personFour.gender)

        val personSeven = persons[7]

        assertNotNull(personSeven)
        assertNotNull(personSeven.id)
        assertNotNull(personSeven.links)
        assertTrue(personSeven.links.toString().contains("</v2/person/7>;rel=\"self\""))
        assertEquals("Address Test7", personSeven.address)
        assertEquals("First Name Test7", personSeven.firstName)
        assertEquals("Last Name Test7", personSeven.lastName)
        assertEquals("Female", personSeven.gender)
    }

    @Test
    fun findById() {
        val person = inputObject.mockEntity(1)
        person.id = 1
        `when`(repository.findById(1)).thenReturn(Optional.of(person))

        val result = service.findById(1)

        assertNotNull(result)
        assertNotNull(result.id)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("</v2/person/1>;rel=\"self\""))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)
    }

    @Test
    fun create() {
        val entity = inputObject.mockEntity(1)

        val persisted = entity.copy()
        persisted.id = 1

        `when`(repository.save(entity)).thenReturn(persisted)

        val vo = inputObject.mockVOv2(1)
        val result = service.create(vo)

        assertNotNull(result)
        assertNotNull(result.id)
        assertNotNull(result.links)
        println(result.links)
        assertTrue(result.links.toString().contains("</v2/person/1>;rel=\"self\""))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)
    }

    @Test
    fun createWithNullPerson() {
        val exception: Exception = assertThrows(
            RequiredObjectIsNullException::class.java
        ) { service.create(null) }

        val expectedMessage = "It is not allowed to persist a null object!"
        val actualMessage = exception.message
        assertTrue(actualMessage!!.contains(expectedMessage))
    }

    @Test
    fun update() {
        val entity = inputObject.mockEntity(1)

        val persisted = entity.copy()
        persisted.id = 1

        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        `when`(repository.save(entity)).thenReturn(persisted)

        val vo = inputObject.mockVOv2(1)
        val result = service.update(vo)

        assertNotNull(result)
        assertNotNull(result.id)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("</v2/person/1>;rel=\"self\""))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)
    }

    @Test
    fun updateWithNullPerson() {
        val exception: Exception = assertThrows(
            RequiredObjectIsNullException::class.java
        ) { service.update(null) }

        val expectedMessage = "It is not allowed to persist a null object!"
        val actualMessage = exception.message
        assertTrue(actualMessage!!.contains(expectedMessage))
    }

    @Test
    fun delete() {
        val entity = inputObject.mockEntity(1)
        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        service.delete(1)
    }
}