package dev.leandro.kotlin_spring_boot.unit.mapper.mocks

import dev.leandro.kotlin_spring_boot.data.vo.v2.BookVO
import dev.leandro.kotlin_spring_boot.model.Book
import dev.leandro.kotlin_spring_boot.util.DateHelper
import java.util.Date


class MockBook {

    fun mockEntityList(): ArrayList<Book> {
        val books: ArrayList<Book> = ArrayList<Book>()
        for (i in 0..13) {
            books.add(mockEntity(i))
        }
        return books
    }

    fun mockVOList(): ArrayList<BookVO> {
        val books: ArrayList<BookVO> = ArrayList()
        for (i in 0..13) {
            books.add(mockVO(i))
        }
        return books
    }

    fun mockEntity(number: Int) = Book(
        id = number.toLong(),
        author = "Some Author$number",
        price = 25.0,
        title = "Some Title$number",
        launchDate = DateHelper.stringToDate("10/11/2022")
    )

    fun mockVO(number: Int) = BookVO(
        id = number.toLong(),
        author = "Some Author$number",
        price = 25.0,
        title = "Some Title$number",
        launchDate = "10/11/2022"
    )
}