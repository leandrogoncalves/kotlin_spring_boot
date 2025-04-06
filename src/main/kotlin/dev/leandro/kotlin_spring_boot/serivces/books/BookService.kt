package dev.leandro.kotlin_spring_boot.serivces.books

import dev.leandro.kotlin_spring_boot.controller.v2.BookController
import dev.leandro.kotlin_spring_boot.data.vo.v2.BookVO
import dev.leandro.kotlin_spring_boot.exceptions.RequiredObjectIsNullException
import dev.leandro.kotlin_spring_boot.exceptions.ResourceNotFoundException
import dev.leandro.kotlin_spring_boot.mapper.custom.BookMapper
import dev.leandro.kotlin_spring_boot.model.Book
import dev.leandro.kotlin_spring_boot.repository.BookRepository
import dev.leandro.kotlin_spring_boot.util.DateHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class BookService {

    @Autowired
    private lateinit var repository: BookRepository

    private val logger = Logger.getLogger(BookService::class.java.name)

    fun findAll(): List<BookVO> {
        logger.info("Finding all books!")
        val books = repository.findAll()
        val vos = BookMapper.mapEntityListToEntityVo(books)
        for (book in vos) {
            val withSelfRel = linkTo(BookController::class.java).slash(book.id).withSelfRel()
            book.add(withSelfRel)
        }
        return vos
    }

    fun findById(id: Long): BookVO {
        logger.info("Finding one book with ID $id!")
        var book = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        val bookVO: BookVO = BookMapper.mapEntityToVO(book)
        val withSelfRel = linkTo(BookController::class.java).slash(bookVO.id).withSelfRel()
        bookVO.add(withSelfRel)
        return bookVO
    }

    fun create(book: BookVO?) : BookVO{
        if (book == null) throw RequiredObjectIsNullException()
        logger.info("Creating one book with title ${book.title}!")
        var entity: Book = BookMapper.mapVOToEntity(book)
        val bookVO: BookVO = BookMapper.mapEntityToVO(repository.save(entity))
        val withSelfRel = linkTo(BookController::class.java).slash(bookVO.id).withSelfRel()
        bookVO.add(withSelfRel)
        return bookVO
    }

    fun update(book: BookVO?) : BookVO{
        if (book == null) throw RequiredObjectIsNullException()
        logger.info("Updating one book with ID ${book.id}!")
        val entity = repository.findById(book.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.author = book.author
        entity.title = book.title
        entity.price = book.price
        entity.launchDate = DateHelper.stringToDate(book.launchDate)
        val bookVO: BookVO = BookMapper.mapEntityToVO(repository.save(entity))
        val withSelfRel = linkTo(BookController::class.java).slash(bookVO.id).withSelfRel()
        bookVO.add(withSelfRel)
        return bookVO
    }

    fun delete(id: Long) {
        logger.info("Deleting one book with ID $id!")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        repository.delete(entity)
    }
}