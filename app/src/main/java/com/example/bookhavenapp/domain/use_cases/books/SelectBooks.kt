package com.example.bookhavenapp.domain.use_cases.books

import com.example.bookhavenapp.domain.model.Item
import com.example.bookhavenapp.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow

class SelectBooks(
    private val booksRepository: BooksRepository
) {
    operator fun invoke(): Flow<List<Item>> {
        return booksRepository.selectBooks()
    }
}
