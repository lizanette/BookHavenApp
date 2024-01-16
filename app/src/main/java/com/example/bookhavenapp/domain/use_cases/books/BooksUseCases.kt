package com.example.bookhavenapp.domain.use_cases.books

data class BooksUseCases(
    val getBooks: GetBooks,
    val searchBooks: SearchBooks,
    val selectBook: SelectBook,
    val upsertBook: UpsertBook,
    val deleteBook: DeleteBook,
    val selectBooks: SelectBooks
)
