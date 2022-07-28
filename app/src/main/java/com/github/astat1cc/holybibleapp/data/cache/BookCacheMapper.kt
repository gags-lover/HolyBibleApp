package com.github.astat1cc.holybibleapp.data.cache

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.data.BookData

interface BookCacheMapper : Abstract.Mapper {

    fun map(id: Int, name: String): BookData

    class Base : BookCacheMapper {

        override fun map(id: Int, name: String) = BookData(id, name)
    }
}