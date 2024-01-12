package com.example.bookhavenapp.presentation.details

import com.example.bookhavenapp.domain.model.Item

sealed class BookDetailsEvent {
    data class UpsertDeleteBook(val item: Item): BookDetailsEvent()

    object RemoveSideEffect: BookDetailsEvent()

    data class CheckIfBookIsSaved(val item: Item, val callback: (Boolean) -> Unit): BookDetailsEvent()
}
