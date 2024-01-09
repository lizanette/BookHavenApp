package com.example.bookhavenapp.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.bookhavenapp.R
import com.example.bookhavenapp.presentation.common.Dimensions.MediumPadding1

@Composable
fun TextTitle(
    text: String
) {
    Text(
        modifier = Modifier.padding(horizontal = MediumPadding1),
        text = text,
        fontSize = 24.sp,
        style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        color = colorResource(id = R.color.text_title),
    )
}
