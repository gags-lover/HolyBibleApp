package com.github.astat1cc.holybibleapp.data

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.core.Book
import com.github.astat1cc.holybibleapp.domain.BooksDomain
import java.lang.Exception

sealed class BooksData : Abstract.Object<BooksDomain, BooksDataToDomainMapper>() {

    class Success(private val books: List<Book>) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper) = mapper.map(books)
    }

    class Fail(private val e: Exception) : BooksData() {

        override fun map(mapper: BooksDataToDomainMapper) = mapper.map(e)
    }
}