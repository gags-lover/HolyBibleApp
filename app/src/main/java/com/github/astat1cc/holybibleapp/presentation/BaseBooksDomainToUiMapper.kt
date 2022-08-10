package com.github.astat1cc.holybibleapp.presentation

import com.github.astat1cc.holybibleapp.R
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

    override fun map(errorType: ErrorType): BooksUi.Fail {
        val errorMessage = when (errorType) {
            ErrorType.NO_CONNECTION -> resourceProvider.getString(R.string.no_connection_error_message)
            ErrorType.SERVICE_UNAVAILABLE -> resourceProvider.getString(R.string.service_unavailable_error_message)
            ErrorType.GENERIC_EXCEPTION -> resourceProvider.getString(R.string.something_went_wrong_error_message)
        }
        return BooksUi.Fail(
            listOf(BookUi.Fail(errorMessage))
        )
    }
}