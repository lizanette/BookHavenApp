package com.example.bookhavenapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.bookhavenapp.data.remote.BooksApi
import com.example.bookhavenapp.data.remote.BooksPagingSource
import com.example.bookhavenapp.data.remote.SearchBooksPagingSource
import com.example.bookhavenapp.domain.model.Item
import com.example.bookhavenapp.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow

class BooksRepositoryImplementation(
    private val booksApi: BooksApi
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
}
