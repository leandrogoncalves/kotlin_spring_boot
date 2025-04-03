package dev.leandro.kotlin_spring_boot.repository

import dev.leandro.kotlin_spring_boot.model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: JpaRepository<Person, Long?>