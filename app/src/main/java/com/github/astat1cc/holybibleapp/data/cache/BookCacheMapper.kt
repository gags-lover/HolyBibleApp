package com.github.astat1cc.holybibleapp.data.cache

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.core.Book
import com.github.astat1cc.holybibleapp.data.BooksCacheMapper

interface BookCacheMapper : Abstract.Mapper {

    fun map(id: Int, name: String): Book

    class Base : BookCacheMapper {

        override fun map(id: Int, name: String) = Book(id, name)
    }
}