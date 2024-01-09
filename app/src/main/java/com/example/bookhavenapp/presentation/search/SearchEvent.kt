package com.example.bookhavenapp.presentation.search

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String): SearchEvent()

    object SearchBooks: SearchEvent()
}
