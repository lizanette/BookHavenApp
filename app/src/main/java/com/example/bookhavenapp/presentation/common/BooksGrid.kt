package com.example.bookhavenapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.bookhavenapp.domain.model.Item
import com.example.bookhavenapp.presentation.common.Dimensions.MediumPadding1


@Composable
fun BooksGrid(
    modifier: Modifier = Modifier,
    books: LazyPagingItems<Item>,
    onClick: (Item) -> Unit
) {
    val handlePagingResult = handlePagingResult(items = books)
    if (handlePagingResult) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            content = {
                items(count = books.itemCount) {
                    it ->
                        books[it]?.let {
                            BookItem(item = it, onClick = { onClick(it) })
                        }
                }
            }
        )
    }
}

// If we get true from this, it means we loaded the items successfully
@Composable
fun handlePagingResult(
    items: LazyPagingItems<Item>
): Boolean {
    val loadState = items.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            true
        }
        error != null -> {
            EmptyScreen()
            false
        }
        else -> {
            true
        }
    }
}

@Composable
private fun ShimmerEffect() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        content = {
            items(count = 10) {
                BookItemShimmerEffect(
                    modifier = Modifier.padding(horizontal = MediumPadding1)
                )
            }
        }
    )
}
