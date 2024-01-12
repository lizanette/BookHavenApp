package com.example.bookhavenapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.bookhavenapp.R

@Composable
fun CategoryTag(category: String) {
    val colors = arrayOf(
        Color(0xFFD32F2F),
        Color(0xFF8E24AA),
        Color(0xFF689F38),
        Color(0xFFEF6C00),
    )
    val randomColor = colors.random()

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(randomColor)
    ) {
        Text(
            category,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            color = Color.White
        )
    }
}
