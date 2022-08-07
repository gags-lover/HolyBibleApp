package com.github.astat1cc.holybibleapp.data

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.data.network.BookCloud
import com.github.astat1cc.holybibleapp.data.network.BookCloudToDataMapper

interface BooksCloudToDataMapper : Abstract.Mapper {

    fun map(books: List<BookCloud>): List<BookData>

    class Base(private val bookMapper: BookCloudToDataMapper) : BooksCloudToDataMapper {

        override fun map(books: List<BookCloud>) = books.map { bookCloud ->
            bookCloud.map(bookMapper)
        }
    }
}