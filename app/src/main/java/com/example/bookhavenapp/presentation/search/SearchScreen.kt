package com.example.bookhavenapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bookhavenapp.presentation.common.BooksGrid
import com.example.bookhavenapp.presentation.common.Dimensions.MediumPadding1
import com.example.bookhavenapp.presentation.common.SearchBar
import com.example.bookhavenapp.presentation.navgraph.Route

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = {
                event(SearchEvent.UpdateSearchQuery(it))
            },
            onSearch = {
                event(SearchEvent.SearchBooks)
            }
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        state.items?.let {
            val items = it.collectAsLazyPagingItems()
            BooksGrid(
                modifier = Modifier.padding(horizontal = MediumPadding1),
                books = items,
                onClick = {
                    navigate(Route.BookDetailsScreen.route)
                }
            )
        }
    }
}
