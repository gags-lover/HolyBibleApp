package com.github.astat1cc.holybibleapp.presentation

import com.github.astat1cc.holybibleapp.core.Abstract

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication>() {

    class Success(
        private val books: List<BookUi>
    ) : BooksUi() {

        override fun map(mapper: BooksCommunication) {
            mapper.map(books)
        }
    }

    class Fail(
        private val books: List<BookUi>
    ) : BooksUi() {

        override fun map(mapper: BooksCommunication) {
            mapper.map(books)
        }
    }
}