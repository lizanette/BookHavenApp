package com.example.bookhavenapp.data.remote

import com.example.bookhavenapp.data.remote.dto.BooksResponse
import com.example.bookhavenapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") q: String = "books",
        @Query("startIndex") startIndex: Int,
        @Query("orderBy") orderBy: String = "newest",
        @Query("key") key: String = API_KEY
    ): BooksResponse

}