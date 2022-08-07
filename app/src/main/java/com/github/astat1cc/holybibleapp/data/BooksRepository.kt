package com.github.astat1cc.holybibleapp.data

interface BooksRepository {

    suspend fun fetchBooks(): BooksData

    class Base(
        private val cloudDataSource: BooksCloudDataSource,
        private val cacheDataSource: BooksCacheDataSource,
        private val booksCloudMapper: BooksCloudToDataMapper,
        private val booksCacheMapper: BooksCacheToDataMapper
    ) : BooksRepository {

        override suspend fun fetchBooks(): BooksData = try {
            val booksCache = cacheDataSource.fetchBooks()
            if (booksCache.isEmpty()) {
                val booksCloud = cloudDataSource.fetchBooks()
                val books = booksCloudMapper.map(booksCloud)
                cacheDataSource.saveBooks(books)
                BooksData.Success(books)
            } else {
                BooksData.Success(booksCacheMapper.map(booksCache))
            }
        } catch (e: Exception) {
            BooksData.Fail(e)
        }
    }
}