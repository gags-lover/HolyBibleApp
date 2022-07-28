package com.github.astat1cc.holybibleapp.presentation

import com.github.astat1cc.holybibleapp.R
import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.domain.ErrorType

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication>() {

    class Success(
        private val books: List<BookUi>
    ) : BooksUi() {

        override fun map(mapper: BooksCommunication) {
            mapper.map(books)
        }
    }

    class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : BooksUi() {

        override fun map(mapper: BooksCommunication) {
            val errorMessage = when (errorType) {
                ErrorType.NO_CONNECTION -> resourceProvider.getString(R.string.no_connection_error_message)
                ErrorType.SERVICE_UNAVAILABLE -> resourceProvider.getString(R.string.service_unavailable_error_message)
                ErrorType.GENERIC_EXCEPTION -> resourceProvider.getString(R.string.something_went_wrong_error_message)
            }
            mapper.map(listOf(BookUi.Fail(errorMessage)))
        }
    }
}