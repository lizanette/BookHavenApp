package com.example.bookhavenapp.domain.use_cases.books

import androidx.paging.PagingData
import com.example.bookhavenapp.domain.model.Item
import com.example.bookhavenapp.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow

class GetBooks(
    private val booksRepository: BooksRepository
) {
    operator fun invoke(): Flow<PagingData<Item>> {
        return booksRepository.getBooks()
    }
}