package com.example.foodie.ui.composable.image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.example.foodie.R
import com.example.foodie.ui.composable.VerticalSpacer
import com.example.foodie.ui.theme.Padding
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FoodieImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    failureDisplay: @Composable () -> Unit = { FailureIcon() },
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
        failure = { failureDisplay()})

}

@Composable
private fun FailureIcon() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        VerticalSpacer(height = Padding.padding10)
        Icon(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.ic_error_round),
            contentDescription = "error",
//            tint = BaseColors.Gray50
        )
        VerticalSpacer(height = Padding.padding5)
        Text(
            text = stringResource(id = R.string.failed_loading_icon),
        )
    }

}