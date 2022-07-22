package com.github.astat1cc.holybibleapp.data

interface BooksRepository {

    suspend fun fetchBooks(): BookData

    class Base(
        private val cloudDataSource: BooksCloudDataSource,
        private val cacheDataSource: BooksCacheDataSource,
        private val booksCloudMapper: BooksCloudMapper,
        private val booksCacheMapper: BooksCacheMapper
    ) : BooksRepository {

        override suspend fun fetchBooks(): BookData = try {
            val booksCache = cacheDataSource.fetchBooks()
            if (booksCache.isEmpty()) {
                val booksCloud = cloudDataSource.fetchBooks()
                val books = booksCloudMapper.map(booksCloud)
                cacheDataSource.saveBooks(books)
                BookData.Success(books)
            } else {
                BookData.Success(booksCacheMapper.map(booksCache))
            }
        } catch (e: Exception) {
            BookData.Fail(e)
        }
    }
}