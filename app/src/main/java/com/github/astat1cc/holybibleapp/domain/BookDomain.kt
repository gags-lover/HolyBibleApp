package com.github.astat1cc.holybibleapp.domain

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.data.TestamentType
import com.github.astat1cc.holybibleapp.presentation.BookUi

sealed class BookDomain : Abstract.Object<BookUi, BookDomainToUiMapper>() {

    class Base(
        private val id: Int,
        private val name: String
    ) : BookDomain() {

        override fun map(mapper: BookDomainToUiMapper) = mapper.map(id, name)
    }

    class Testament(
        private val type: TestamentType
    ) : BookDomain() {

        override fun map(mapper: BookDomainToUiMapper) = mapper.map(type.getId(), type.name)
    }
}