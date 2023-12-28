package com.example.bookhavenapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.bookhavenapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf<Page>(
    Page(
        title = "Search through thousands of books",
        description = "Lorem Ipsum lorem ipsum lorem ipsum ist",
        image = R.drawable.onboarding2_bookshelf
    ),
    Page(
        title = "Discover new ones",
        description = "Lorem Ipsum lorem ipsum lorem ipsum ist",
        image = R.drawable.onboarding1_book_stack
    ),
    Page(
        title = "Save your favorites",
        description = "Lorem Ipsum lorem ipsum lorem ipsum ist",
        image = R.drawable.onboarding3_cozy_book
    )
)
