package com.example.bookhavenapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.bookhavenapp.data.local.BookDao
import com.example.bookhavenapp.data.remote.BooksApi
import com.example.bookhavenapp.data.remote.BooksPagingSource
import com.example.bookhavenapp.data.remote.SearchBooksPagingSource
import com.example.bookhavenapp.domain.model.Item
import com.example.bookhavenapp.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow

class BooksRepositoryImplementation(
    private val booksApi: BooksApi,
    private val bookDao: BookDao
): BooksRepository {
    override fun getBooks(): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                BooksPagingSource(
                    booksApi = booksApi
                )
            }
        ).flow
    }

    override fun searchBooks(searchQuery: String): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchBooksPagingSource(
                    searchQuery = searchQuery,
                    booksApi = booksApi
                )
            }
        ).flow
    }

    override suspend fun selectBook(id: String): Item? {
        return bookDao.getBook(id)
    }

    override suspend fun upsertBook(item: Item) {
        bookDao.upsert(item)
    }

    override suspend fun deleteBook(item: Item) {
        bookDao.delete(item)
    }

    override fun selectBooks(): Flow<List<Item>> {
        return bookDao.getBooks()
    }
}
