package com.example.bookhavenapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookhavenapp.domain.model.Item
import com.example.bookhavenapp.domain.use_cases.books.BooksUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val booksUseCases: BooksUseCases
): ViewModel() {
    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: BookDetailsEvent) {
        when(event) {
            is BookDetailsEvent.UpsertDeleteBook -> {
                viewModelScope.launch {
                    val book = booksUseCases.selectBook(event.item.id)
                    if(book == null) {
                        upsertBook(event.item)
                    } else {
                        deleteBook(event.item)
                    }
                }
            }
            is BookDetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
            is BookDetailsEvent.CheckIfBookIsSaved -> {
                viewModelScope.launch {
                    val isBookSaved = booksUseCases.selectBook(event.item.id) != null
                    event.callback(isBookSaved)
                }
            }
        }
    }

    private suspend fun upsertBook(item: Item) {
        booksUseCases.upsertBook(item = item)
        sideEffect = "Book saved successfully"
    }

    private suspend fun deleteBook(item: Item) {
        booksUseCases.deleteBook(item = item)
        sideEffect = "Book deleted from Bookmark list"
    }
}
