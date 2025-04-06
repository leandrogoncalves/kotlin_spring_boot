package dev.leandro.kotlin_spring_boot.mapper.custom

import dev.leandro.kotlin_spring_boot.data.vo.v2.BookVO
import dev.leandro.kotlin_spring_boot.model.Book
import dev.leandro.kotlin_spring_boot.util.DateHelper
import org.springframework.stereotype.Service

@Service
object BookMapper {

    fun mapEntityToVO(book: Book): BookVO {
        val vo = BookVO()
        vo.id = book.id
        vo.author = book.author
        vo.launchDate = DateHelper.dateToString(book.launchDate)
        vo.price = book.price
        vo.title = book.title
        return vo
    }

    fun mapVOToEntity(book: BookVO): Book {
        val entity = Book()
        entity.id = book.id
        entity.author = book.author
        entity.launchDate = DateHelper.stringToDate(book.launchDate)
        entity.price = book.price
        entity.title = book.title
        return entity
    }

    fun mapEntityListToEntityVo(bookList: List<Book>): ArrayList<BookVO> {
        val outputList: ArrayList<BookVO> = ArrayList()
        bookList.forEach {
            outputList.add(
                BookVO(
                    id = it.id,
                    title = it.title,
                    author = it.author,
                    launchDate = DateHelper.dateToString(it.launchDate),
                    price = it.price
                )
            )
        }
        return outputList
    }
}
