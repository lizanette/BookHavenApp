package com.example.bookhavenapp.presentation.search

import androidx.paging.PagingData
import com.example.bookhavenapp.domain.model.Item
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val items: Flow<PagingData<Item>>? = null
)
