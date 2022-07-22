package com.github.astat1cc.holybibleapp.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.core.Book
import com.github.astat1cc.holybibleapp.data.cache.BookCache.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class BookCache(
    @PrimaryKey val id: Int = 1,
    val name: String
) : Abstract.Object<Book, BookCacheMapper>() {

    override fun map(mapper: BookCacheMapper) = mapper.map(id, name)

    companion object {

        const val TABLE_NAME = "books"

        fun fromBook(book: Book) = BookCache(book.id, book.name)
    }
}