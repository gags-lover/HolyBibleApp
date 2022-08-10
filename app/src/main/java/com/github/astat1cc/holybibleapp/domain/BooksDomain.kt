package com.github.astat1cc.holybibleapp.domain

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.presentation.BooksUi
import retrofit2.HttpException
import java.net.UnknownHostException

sealed class BooksDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper>() {

    class Success(private val books: List<BookDomain>) : BooksDomain() {

        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(books)
    }

    class Fail(private val errorType: ErrorType) : BooksDomain() {

        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(errorType)
    }
}