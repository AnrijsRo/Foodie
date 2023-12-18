package com.example.foodie.ui.composable.shimmer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.ShimmerTheme
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

class ShimmerStyles {
    companion object {
        val defaultShimmer = ShimmerTheme(
            animationSpec = infiniteRepeatable(
                animation = tween(
                    1000,
                    easing = LinearEasing,
                    delayMillis = 100,
                ),
                repeatMode = RepeatMode.Restart,
            ),
            blendMode = BlendMode.SrcIn,
            rotation = 15.0f,
            shaderColors = listOf(
                Color.Unspecified.copy(alpha = 0.25f),
                Color.Unspecified.copy(alpha = 0.4f),
                Color.Unspecified.copy(alpha = 0.25f),
            ),
            shaderColorStops = listOf(
                0.0f,
                0.5f,
                0.7f,
            ),
            shimmerWidth = 400.dp,
        )
        val shimmerOver = ShimmerTheme(
            animationSpec = infiniteRepeatable(
                animation = tween(
                    1000,
                    easing = LinearEasing,
                    delayMillis = 100,
                ),
                repeatMode = RepeatMode.Restart,
            ),
            blendMode = BlendMode.DstIn,
            rotation = 15.0f,
            shaderColors = listOf(
                Color.Unspecified.copy(alpha = 0.25f),
                Color.Unspecified.copy(alpha = 0.4f),
                Color.Unspecified.copy(alpha = 0.25f),
            ),
            shaderColorStops = listOf(
                0.0f,
                0.5f,
                0.7f,
            ),
            shimmerWidth = 400.dp,
        )
    }

}

fun Modifier.shimmerOver(): Modifier = composed {
    val shimmer = rememberShimmer(
        shimmerBounds = ShimmerBounds.View,
        theme = ShimmerStyles.shimmerOver
    )
    shimmer(customShimmer = shimmer)
}
