package com.github.astat1cc.holybibleapp.data

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.core.Book
import com.github.astat1cc.holybibleapp.domain.BooksDomain

interface BookDataToDomainMapper : Abstract.Mapper {

    fun map(books: List<Book>): BooksDomain

    fun map(e: Exception): BooksDomain
}