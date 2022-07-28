package com.github.astat1cc.holybibleapp.domain

import com.github.astat1cc.holybibleapp.data.BookDataToDomainMapper
import org.koin.dsl.module

val domainModule = module {
    factory<BooksInteractor> {
        BooksInteractor.Base(repository = get(), mapper = BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper()))
    }
}