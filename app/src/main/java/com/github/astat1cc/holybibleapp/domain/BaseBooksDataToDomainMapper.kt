package com.github.astat1cc.holybibleapp.domain

import com.github.astat1cc.holybibleapp.core.Book
import com.github.astat1cc.holybibleapp.data.BooksDataToDomainMapper

class BaseBooksDataToDomainMapper : BooksDataToDomainMapper {

    override fun map(books: List<Book>) = BookDomain.Success(books)

    override fun map(e: Exception) = BookDomain.Fail(e)
}