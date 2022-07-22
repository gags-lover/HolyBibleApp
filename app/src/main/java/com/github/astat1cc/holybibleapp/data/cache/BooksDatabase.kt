package com.github.astat1cc.holybibleapp.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [BookCache::class]
)
abstract class BooksDatabase : RoomDatabase() {

    abstract fun booksDao(): BooksDao

    companion object {

        const val DATABASE_NAME = "bible_database"
    }
}