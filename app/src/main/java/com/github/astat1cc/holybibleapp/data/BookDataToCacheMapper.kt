package com.github.astat1cc.holybibleapp.data

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.data.cache.BookCache

interface BookDataToCacheMapper : Abstract.Mapper {

    fun map(id: Int, name: String): BookCache

    class Base : BookDataToCacheMapper {

        override fun map(id: Int, name: String) = BookCache(id, name)
    }
}