package com.github.astat1cc.holybibleapp.data.network

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.data.BookData

data class BookCloud(
    private val id: Int,
    private val name: String,
    private val testament: String
) : Abstract.Object<BookData, BookCloudToDataMapper>() {

    override fun map(mapper: BookCloudToDataMapper) = mapper.map(id, name, testament)
}