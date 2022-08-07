package com.github.astat1cc.holybibleapp.domain

import com.github.astat1cc.holybibleapp.data.BookDataToDomainMapper

class BaseBookDataToDomainMapper : BookDataToDomainMapper {

    override fun map(id: Int, name: String) = BookDomain.Base(id, name)
}