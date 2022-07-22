package com.github.astat1cc.holybibleapp.data

import com.github.astat1cc.holybibleapp.core.Book
import com.github.astat1cc.holybibleapp.data.cache.BookCache
import com.github.astat1cc.holybibleapp.data.cache.BooksDao

interface BooksCacheDataSource {

    suspend fun fetchBooks(): List<BookCache>

    suspend fun saveBooks(books: List<Book>)

    class Base(private val booksDao: BooksDao) : BooksCacheDataSource {

        override suspend fun fetchBooks() = booksDao.fetchBooks() ?: emptyList()

        override suspend fun saveBooks(books: List<Book>) {
            books.forEach { book ->
                booksDao.saveBook(BookCache.fromBook(book))
            }
        }
    }
}