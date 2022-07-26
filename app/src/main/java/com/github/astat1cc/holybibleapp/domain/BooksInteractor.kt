package com.github.astat1cc.holybibleapp.domain

import com.github.astat1cc.holybibleapp.data.BooksDataToDomainMapper
import com.github.astat1cc.holybibleapp.data.BooksRepository

interface BooksInteractor {

    suspend fun fetchBooks(): BooksDomain

    class Base(
        private val repository: BooksRepository,
        private val mapper: BooksDataToDomainMapper
    ) : BooksInteractor {

        override suspend fun fetchBooks() = repository.fetchBooks().map(mapper)
    }
}