package com.github.astat1cc.holybibleapp.data

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.domain.BooksDomain

interface BooksDataToDomainMapper : Abstract.Mapper {

    fun map(books: List<BookData>): BooksDomain

    fun map(e: Exception): BooksDomain
}