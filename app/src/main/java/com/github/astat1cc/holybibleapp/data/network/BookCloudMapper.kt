package com.github.astat1cc.holybibleapp.data.network

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.core.Book
import com.github.astat1cc.holybibleapp.data.BookData

interface BookCloudMapper : Abstract.Mapper {

    fun map(id: Int, name: String): Book

    class Base : BookCloudMapper {

        override fun map(id: Int, name: String) = Book(id, name)
    }
}