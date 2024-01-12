package com.example.bookhavenapp.domain.use_cases.books

import com.example.bookhavenapp.domain.model.Item
import com.example.bookhavenapp.domain.repository.BooksRepository

class SelectBook(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(id: String): Item? {
        return booksRepository.selectBook(id)
    }
}
