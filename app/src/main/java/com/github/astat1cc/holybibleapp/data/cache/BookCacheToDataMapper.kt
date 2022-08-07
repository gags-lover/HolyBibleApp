package com.github.astat1cc.holybibleapp.data.cache

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.data.BookData

interface BookCacheToDataMapper : Abstract.Mapper {

    fun map(id: Int, name: String, testament: String): BookData

    class Base : BookCacheToDataMapper {

        override fun map(id: Int, name: String, testament: String) = BookData(id, name, testament)
    }
}