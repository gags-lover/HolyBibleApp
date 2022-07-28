package com.github.astat1cc.holybibleapp.presentation

import com.github.astat1cc.holybibleapp.domain.BookDomainToUiMapper

class BaseBookDomainToUiMapper : BookDomainToUiMapper {

    override fun map(id: Int, name: String) = BookUi.Base(id, name)
}