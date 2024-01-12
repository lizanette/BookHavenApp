package com.example.bookhavenapp.domain.repository

import androidx.paging.PagingData
import com.example.bookhavenapp.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    // Remote
    fun getBooks(): Flow<PagingData<Item>>
    fun searchBooks(searchQuery: String): Flow<PagingData<Item>>

    // Local
    suspend fun selectBook(id: String): Item?
    suspend fun upsertBook(item: Item)
    suspend fun deleteBook(item: Item)
    fun selectBooks(): Flow<List<Item>>
}
