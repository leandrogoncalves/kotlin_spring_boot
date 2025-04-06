package dev.leandro.kotlin_spring_boot.repository

import dev.leandro.kotlin_spring_boot.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long?>