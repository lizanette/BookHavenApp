package com.example.bookhavenapp.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookhavenapp.domain.use_cases.books.BooksUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val booksUseCases: BooksUseCases
): ViewModel() {
    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    init {
        getBooks()
    }

    private fun getBooks() {
        booksUseCases.selectBooks().onEach {
            _state.value = _state.value.copy(books = it.asReversed())
        }.launchIn(viewModelScope)
    }
}
