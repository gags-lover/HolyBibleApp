package com.github.astat1cc.holybibleapp.data

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.core.Book
import com.github.astat1cc.holybibleapp.data.cache.BookCache
import com.github.astat1cc.holybibleapp.data.cache.BookCacheMapper

interface BooksCacheMapper : Abstract.Mapper {

    fun map(books:List<BookCache>): List<Book>

    class Base(private val bookMapper: BookCacheMapper) : BooksCacheMapper {

        override fun map(books:List<BookCache>) = books.map { bookCache ->
            bookCache.map(bookMapper)
        }
    }
}