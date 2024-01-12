package com.example.bookhavenapp.presentation.bookmark

import com.example.bookhavenapp.domain.model.Item

data class BookmarkState(
    val books: List<Item> = emptyList()
)
