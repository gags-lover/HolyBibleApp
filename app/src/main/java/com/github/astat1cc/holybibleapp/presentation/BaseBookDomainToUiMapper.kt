package com.github.astat1cc.holybibleapp.presentation

import com.github.astat1cc.holybibleapp.R
import com.github.astat1cc.holybibleapp.data.TestamentType
import com.github.astat1cc.holybibleapp.domain.BookDomainToUiMapper

class BaseBookDomainToUiMapper(
    private val resourceProvider: ResourceProvider
) : BookDomainToUiMapper {

    override fun map(id: Int, name: String) =
        when (id) {
            TestamentType.OLD.getId() ->
                BookUi.Testament(id, resourceProvider.getString(R.string.old_testament))
            TestamentType.NEW.getId() ->
                BookUi.Testament(id, resourceProvider.getString(R.string.new_testament))
            else -> BookUi.Base(id, name)
        }
}