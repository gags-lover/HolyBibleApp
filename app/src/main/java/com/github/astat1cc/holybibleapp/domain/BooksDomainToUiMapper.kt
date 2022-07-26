package com.github.astat1cc.holybibleapp.domain

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.core.Book
import com.github.astat1cc.holybibleapp.presentation.BooksUi

interface BooksDomainToUiMapper : Abstract.Mapper {

    fun map(books: List<Book>): BooksUi

    fun map(e: ErrorType): BooksUi
}