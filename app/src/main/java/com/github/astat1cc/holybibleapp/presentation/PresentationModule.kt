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
        BaseBooksDomainToUiMapper(
            resourceProvider = get(),
            mapper = BaseBookDomainToUiMapper(resourceProvider = get())
        )
    }
    single<CollapsedTestamentsCache> {
        CollapsedTestamentsCache.Base(androidContext())
    }
    single<UiDataCache> {
        UiDataCache.Base(collapsedTestamentsCache = get())
    }
    viewModel {
        MainViewModel(
            interactor = get(),
            mapper = get(),
            communication = get(),
            uiDataCache = get()
        )
    }
}