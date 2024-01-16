package com.example.bookhavenapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.bookhavenapp.R
import com.example.bookhavenapp.domain.model.Item
import com.example.bookhavenapp.presentation.common.BooksGrid
import com.example.bookhavenapp.presentation.common.Dimensions.MediumPadding1
import com.example.bookhavenapp.presentation.common.Dimensions.MediumPadding3
import com.example.bookhavenapp.presentation.common.SearchBar
import com.example.bookhavenapp.presentation.common.TextTitle

@Composable
fun HomeScreen(
    books: LazyPagingItems<Item>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Item) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            modifier = Modifier
                .width(270.dp)
                .height(45.dp)
                .padding(horizontal = MediumPadding1),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "App Logo",
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        SearchBar(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                navigateToSearch()
            },
            onSearch = {}
        )

        Spacer(modifier = Modifier.height(MediumPadding3))

        TextTitle(text = "Recent Books")

        Spacer(modifier = Modifier.height(MediumPadding3))

        BooksGrid(
            modifier = Modifier.padding(horizontal = 18.dp),
            books = books,
            onClick = {
                navigateToDetails(it)
            }
        )
    }
}
