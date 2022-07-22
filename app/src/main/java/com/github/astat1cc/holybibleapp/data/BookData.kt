package com.github.astat1cc.holybibleapp.data

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.core.Book
import com.github.astat1cc.holybibleapp.domain.BookDomain
import java.lang.Exception

sealed class BookData : Abstract.Object<BookDomain, BookDataToDomainMapper>() {

    class Success(private val books: List<Book>) : BookData() {

        override fun map(mapper: BookDataToDomainMapper) = mapper.map(books)
    }

    class Fail(private val e: Exception) : BookData() {

        override fun map(mapper: BookDataToDomainMapper) = mapper.map(e)
    }
}