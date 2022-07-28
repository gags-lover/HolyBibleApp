package com.github.astat1cc.holybibleapp.data

import com.github.astat1cc.holybibleapp.data.cache.BookCache
import com.github.astat1cc.holybibleapp.data.cache.BooksDao

interface BooksCacheDataSource {

    suspend fun fetchBooks(): List<BookCache>

    suspend fun saveBooks(books: List<BookData>)

    class Base(
        private val booksDao: BooksDao,
        private val mapper: BookDataToCacheMapper
    ) : BooksCacheDataSource {

        override suspend fun fetchBooks() = booksDao.fetchBooks() ?: emptyList()

        override suspend fun saveBooks(books: List<BookData>) {
            books.forEach { book ->
                booksDao.saveBook(book.toCache(mapper))
            }
        }
    }
}