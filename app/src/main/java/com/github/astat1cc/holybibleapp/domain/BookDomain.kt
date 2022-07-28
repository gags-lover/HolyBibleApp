package com.github.astat1cc.holybibleapp.domain

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.presentation.BookUi

data class BookDomain(
    private val id: Int,
    private val name: String
) : Abstract.Object<BookUi, BookDomainToUiMapper>() {

    override fun map(mapper: BookDomainToUiMapper) = mapper.map(id, name)
}