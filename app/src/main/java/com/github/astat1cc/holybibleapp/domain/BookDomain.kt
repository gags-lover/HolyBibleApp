package com.github.astat1cc.holybibleapp.domain

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.core.Book
import com.github.astat1cc.holybibleapp.presentation.BooksUi
import retrofit2.HttpException
import java.net.UnknownHostException

// TODO: rename to BooksDomain by lead

sealed class BookDomain : Abstract.Object<BooksUi, BookDomainToUiMapper>() {

    class Success(private val books: List<Book>) : BookDomain() {

        override fun map(mapper: BookDomainToUiMapper) = mapper.map(books)
    }

    class Fail(private val e: Exception) : BookDomain() {

        override fun map(mapper: BookDomainToUiMapper) = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_EXCEPTION
            }
        )
    }
}