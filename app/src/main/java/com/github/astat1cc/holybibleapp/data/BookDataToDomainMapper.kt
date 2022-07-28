package com.github.astat1cc.holybibleapp.data

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.domain.BookDomain

interface BookDataToDomainMapper : Abstract.Mapper {

    fun map(id: Int, name: String): BookDomain
}