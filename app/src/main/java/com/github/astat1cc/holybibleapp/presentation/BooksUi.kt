package com.github.astat1cc.holybibleapp.presentation

import com.github.astat1cc.holybibleapp.core.Abstract

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication>() {

    abstract fun cache(uiDataCache: UiDataCache): BooksUi

    class Base(
        private val books: List<BookUi>
    ) : BooksUi() {

        override fun map(mapper: BooksCommunication) {
            mapper.map(books)
        }

        override fun cache(uiDataCache: UiDataCache) = uiDataCache.cache(books)
    }
}