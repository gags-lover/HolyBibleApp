package com.github.astat1cc.holybibleapp.presentation

import com.github.astat1cc.holybibleapp.domain.BooksDomainToUiMapper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    single<BooksCommunication> {
        BooksCommunication.Base()
    }
    single<ResourceProvider> {
        ResourceProvider.Base(androidContext())
    }
    single<BooksDomainToUiMapper> {
        BaseBooksDomainToUiMapper(communication = get(), resourceProvider = get())
    }
    viewModel {
        MainViewModel(
            interactor = get(),
            mapper = get(),
            communication = get()
        )
    }
}