package com.github.astat1cc.holybibleapp.domain

import org.koin.dsl.module

val domainModule = module {
    factory<BooksInteractor> {
        BooksInteractor.Base(repository = get(), mapper = BaseBooksDataToDomainMapper())
    }
}