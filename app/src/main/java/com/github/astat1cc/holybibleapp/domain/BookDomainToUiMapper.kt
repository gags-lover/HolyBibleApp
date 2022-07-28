package com.github.astat1cc.holybibleapp.domain

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.presentation.BookUi

interface BookDomainToUiMapper : Abstract.Mapper {

    fun map(id: Int, name: String): BookUi
}