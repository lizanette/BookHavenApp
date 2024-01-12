package com.example.bookhavenapp.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookhavenapp.R
import com.example.bookhavenapp.domain.model.Item
import com.example.bookhavenapp.presentation.common.BooksGrid
import com.example.bookhavenapp.presentation.common.Dimensions
import com.example.bookhavenapp.presentation.common.Dimensions.MediumPadding1
import com.example.bookhavenapp.presentation.common.EmptyContent
import com.example.bookhavenapp.presentation.common.TextTitle

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Item) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        TextTitle(text = "Saved Books")

        Spacer(modifier = Modifier.height(Dimensions.MediumPadding3))

        if(state.books.isEmpty()) {
            EmptyContent(
                message = "You haven't saved any books so far :(",
                iconId = R.drawable.ic_search_document
            )
        } else {
            BooksGrid(
                modifier = Modifier.padding(horizontal = 18.dp),
                books = state.books,
                onClick = {
                    navigateToDetails(it)
                }
            )
        }
    }
}
