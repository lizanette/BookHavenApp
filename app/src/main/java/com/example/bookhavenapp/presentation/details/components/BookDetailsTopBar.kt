package com.example.bookhavenapp.presentation.details.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookhavenapp.R
import com.example.bookhavenapp.ui.theme.BookHavenAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsTopBar(
    onPreviewClick: () -> Unit,
    onGetInfoClick: () -> Unit,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {},
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(onClick = onPreviewClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_preview),
                    contentDescription = "Preview"
                )
            }
            IconButton(onClick = onGetInfoClick) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Get more information"
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailsTopBarPreview() {
    BookHavenAppTheme {
        BookDetailsTopBar(
            onPreviewClick = {},
            onGetInfoClick = {},
            onShareClick = {}
        ) {}
    }
}