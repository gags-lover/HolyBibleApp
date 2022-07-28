package com.github.astat1cc.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.astat1cc.holybibleapp.domain.BooksDomainToUiMapper
import com.github.astat1cc.holybibleapp.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val communication: BooksCommunication,
    private val interactor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper
) : ViewModel() {

    fun fetchBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            communication.map(listOf(BookUi.Progress))
            val result: BooksUi = interactor.fetchBooks().map(mapper)
            result.map(communication)
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
        communication.observe(owner, observer)
    }
}