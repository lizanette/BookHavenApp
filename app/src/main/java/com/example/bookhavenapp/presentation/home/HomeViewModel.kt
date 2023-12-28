package com.example.bookhavenapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.bookhavenapp.domain.use_cases.books.BooksUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val booksUseCases: BooksUseCases
): ViewModel() {
    val books = booksUseCases.getBooks().cachedIn(viewModelScope)
}