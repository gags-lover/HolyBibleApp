package com.github.astat1cc.holybibleapp.presentation

import com.github.astat1cc.holybibleapp.domain.BookDomain
import com.github.astat1cc.holybibleapp.domain.BookDomainToUiMapper
import com.github.astat1cc.holybibleapp.domain.BooksDomainToUiMapper
import com.github.astat1cc.holybibleapp.domain.ErrorType

/**
 * todo rename e: ErrorType to  errorType: ErrorType by domain author
 */
class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val mapper: BookDomainToUiMapper
) : BooksDomainToUiMapper {

    override fun map(books: List<BookDomain>) = BooksUi.Success(books.map { bookDomain ->
        bookDomain.map(mapper)
    })

    override fun map(errorType: ErrorType) =
        BooksUi.Fail(errorType, resourceProvider)
}