package com.github.astat1cc.holybibleapp.data.network

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.data.BookData

interface BookCloudToDataMapper : Abstract.Mapper {

    fun map(id: Int, name: String, testament: String): BookData

    class Base : BookCloudToDataMapper {

        override fun map(id: Int, name: String, testament: String) = BookData(id, name, testament)
    }
}