package com.github.astat1cc.holybibleapp.data

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.data.cache.BookCache
import com.github.astat1cc.holybibleapp.data.cache.BookCacheToDataMapper

interface BooksCacheToDataMapper : Abstract.Mapper {

    fun map(books:List<BookCache>): List<BookData>

    class Base(private val bookMapper: BookCacheToDataMapper) : BooksCacheToDataMapper {

        override fun map(books:List<BookCache>) = books.map { bookCache ->
            bookCache.map(bookMapper)
        }
    }
}