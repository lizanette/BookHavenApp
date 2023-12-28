package com.example.bookhavenapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bookhavenapp.domain.model.Item

class BooksPagingSource(
    private val booksApi: BooksApi
): PagingSource<Int, Item>() {

    private var totalBooks = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val startIndex = params.key ?: 0
        return try {
            val booksResponse = booksApi.getBooks(startIndex = startIndex)
            totalBooks += booksResponse.items.size
            val items = booksResponse.items.distinctBy { it.volumeInfo.title } // Remove duplicates
            LoadResult.Page(
                data = items,
                nextKey = if (totalBooks == booksResponse.totalItems) null else startIndex + 10,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let {
                anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(10) ?: anchorPage?.nextKey?.minus(10)
        }
    }
}