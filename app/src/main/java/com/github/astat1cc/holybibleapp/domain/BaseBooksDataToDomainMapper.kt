package com.github.astat1cc.holybibleapp.domain

import com.github.astat1cc.holybibleapp.data.BookData
import com.github.astat1cc.holybibleapp.data.BookDataToDomainMapper
import com.github.astat1cc.holybibleapp.data.BooksDataToDomainMapper

class BaseBooksDataToDomainMapper(
    private val mapper: BookDataToDomainMapper
) : BooksDataToDomainMapper {

    override fun map(books: List<BookData>) = BooksDomain.Success(books.map { it.map(mapper) })

    override fun map(e: Exception) = BooksDomain.Fail(e)
}