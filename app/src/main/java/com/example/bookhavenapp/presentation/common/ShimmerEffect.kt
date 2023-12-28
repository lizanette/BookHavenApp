package com.example.bookhavenapp.presentation.common

import android.content.res.Configuration
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookhavenapp.R
import com.example.bookhavenapp.presentation.common.Dimensions.BookItemHeight
import com.example.bookhavenapp.presentation.common.Dimensions.BookItemWidth
import com.example.bookhavenapp.presentation.common.Dimensions.ExtraSmallPadding
import com.example.bookhavenapp.ui.theme.BookHavenAppTheme

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    ).value
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
}

@Composable
fun BookItemShimmerEffect(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(BookItemHeight)
                .width(BookItemWidth)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect(),
        )
        Spacer(Modifier.height(2.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding)
                .width(BookItemWidth),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .width(BookItemWidth)
                    .shimmerEffect(),
            )
            Spacer(Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width(BookItemWidth)
                    .shimmerEffect(),
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BookItemShimmerEffectPreview() {
    BookHavenAppTheme {
        BookItemShimmerEffect()
    }
}
