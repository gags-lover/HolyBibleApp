package com.github.astat1cc.holybibleapp.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.data.BookData
import com.github.astat1cc.holybibleapp.data.cache.BookCache.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class BookCache(
    @PrimaryKey val id: Int = 1,
    val name: String,
    val testament: String
) : Abstract.Object<BookData, BookCacheToDataMapper>() {

    override fun map(mapper: BookCacheToDataMapper) = mapper.map(id, name, testament)

    companion object {

        const val TABLE_NAME = "books"
    }
}