package com.example.bookhavenapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.bookhavenapp.domain.use_cases.books.BooksUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val booksUseCases: BooksUseCases
): ViewModel() {
    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(searchQuery = event.searchQuery)
            }
            is SearchEvent.SearchBooks -> {
                searchBooks()
            }
        }
    }

    private fun searchBooks() {
        val items = booksUseCases.searchBooks(
            searchQuery = state.value.searchQuery
        ).cachedIn(viewModelScope)

        _state.value = state.value.copy(items = items)
    }
}
