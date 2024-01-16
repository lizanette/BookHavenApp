package com.example.bookhavenapp.presentation.books_navigator.components

import androidx.annotation.DrawableRes
import com.example.bookhavenapp.R

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        icon = R.drawable.ic_home,
        text = "Home"
    ),
    BottomNavigationItem(
        icon = R.drawable.ic_search,
        text = "Explore"
    ),
    BottomNavigationItem(
        icon = R.drawable.ic_bookmark,
        text = "Bookmark"
    )
)
