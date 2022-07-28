package com.github.astat1cc.holybibleapp.data.network

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.data.BookData

data class BookCloud(
    private val id: Int,
    private val name: String
) : Abstract.Object<BookData, BookCloudMapper>() {

    override fun map(mapper: BookCloudMapper) = mapper.map(id, name)
}