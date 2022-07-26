package com.github.astat1cc.holybibleapp.domain

import com.github.astat1cc.holybibleapp.core.Book
import com.github.astat1cc.holybibleapp.data.BooksDataToDomainMapper

class BaseBooksDataToDomainMapper : BooksDataToDomainMapper {

    override fun map(books: List<Book>) = BooksDomain.Success(books)

    override fun map(e: Exception) = BooksDomain.Fail(e)
}