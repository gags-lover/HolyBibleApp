package com.github.astat1cc.holybibleapp.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BooksDao {

    @Query("SELECT * FROM ${BookCache.TABLE_NAME}")
    fun fetchBooks(): List<BookCache>?

    @Insert(entity = BookCache::class, onConflict = OnConflictStrategy.REPLACE)
    fun saveBook(book: BookCache)
}