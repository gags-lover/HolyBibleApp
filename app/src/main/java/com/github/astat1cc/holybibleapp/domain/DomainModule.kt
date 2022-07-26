package com.github.astat1cc.holybibleapp.domain

import com.github.astat1cc.holybibleapp.data.BooksDataToDomainMapper
import org.koin.dsl.module

val interactorModule = module {
    factory {
        BooksInteractor.Base(repository = get(), mapper = BaseBooksDataToDomainMapper())
    }
}