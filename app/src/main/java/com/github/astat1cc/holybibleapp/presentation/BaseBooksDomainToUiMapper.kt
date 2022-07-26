package com.github.astat1cc.holybibleapp.presentation

import com.github.astat1cc.holybibleapp.core.Book
import com.github.astat1cc.holybibleapp.domain.BooksDomainToUiMapper
import com.github.astat1cc.holybibleapp.domain.ErrorType

/**
 * todo rename e: ErrorType to  errorType: ErrorType by domain author
 */
class BaseBooksDomainToUiMapper(
    private val communication: BooksCommunication,
    private val resourceProvider: ResourceProvider
) : BooksDomainToUiMapper {

    override fun map(books: List<Book>) = BooksUi.Success(books, communication)

    override fun map(errorType: ErrorType) =
        BooksUi.Fail(errorType, communication, resourceProvider)
}