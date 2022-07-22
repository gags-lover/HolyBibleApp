package com.github.astat1cc.holybibleapp.data

import com.github.astat1cc.holybibleapp.data.network.BookCloud
import com.github.astat1cc.holybibleapp.data.network.BooksService

interface BooksCloudDataSource {

    suspend fun fetchBooks(): List<BookCloud>

    class Base(private val service: BooksService) : BooksCloudDataSource {

        override suspend fun fetchBooks(): List<BookCloud> = service.fetchBooks()
    }
}