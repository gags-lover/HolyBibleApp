package com.github.astat1cc.holybibleapp.domain

import com.github.astat1cc.holybibleapp.data.*

class BaseBooksDataToDomainMapper(
    private val mapper: BookDataToDomainMapper
) : BooksDataToDomainMapper {

    override fun map(books: List<BookData>): BooksDomain {
        val mappedBooks = mutableListOf<BookDomain>()
        val temp = TestamentTemp.Base()
        books.forEach { bookData ->
            if (!bookData.matches(temp)) {
                bookData.saveTestament(temp)
                mappedBooks.add(BookDomain.Testament(temp.getTestamentType()))
            }
            mappedBooks.add(bookData.map(mapper))
        }

        return BooksDomain.Success(mappedBooks)
    }

    override fun map(e: Exception) = BooksDomain.Fail(e)

    companion object {

        const val NAME_OF_OLD_TESTAMENT_FROM_CLOUD_RESPONSE = "OT"
    }
}

