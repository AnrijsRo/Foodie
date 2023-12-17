package com.example.foodie.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FoodieImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    GlideImage(imageModel = imageUrl,
        modifier = modifier,
        contentScale = contentScale,
        requestBuilder = {
            Glide
                .with(LocalContext.current)
                .asDrawable()
        },
        shimmerParams = ShimmerParams(
            baseColor = Color.Unspecified.copy(alpha = 0.25f),
            highlightColor = Color.Unspecified.copy(alpha = 0.50f)
        ),
        failure = { })

}