package com.github.astat1cc.holybibleapp.data.cache

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.core.Book

interface BookCacheMapper : Abstract.Mapper {

    fun map(id: Int, name: String): Book
}