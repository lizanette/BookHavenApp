package com.example.bookhavenapp.data.remote.dto

import com.example.bookhavenapp.domain.model.Item

data class BooksResponse(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)
