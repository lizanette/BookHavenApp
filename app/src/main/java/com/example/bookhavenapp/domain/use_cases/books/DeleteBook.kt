package com.example.bookhavenapp.domain.use_cases.books

import com.example.bookhavenapp.domain.model.Item
import com.example.bookhavenapp.domain.repository.BooksRepository

class DeleteBook(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(item: Item) {
        booksRepository.deleteBook(item)
    }
}
