package com.example.bookhavenapp.domain.repository

import androidx.paging.PagingData
import com.example.bookhavenapp.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    fun getBooks(): Flow<PagingData<Item>>
}