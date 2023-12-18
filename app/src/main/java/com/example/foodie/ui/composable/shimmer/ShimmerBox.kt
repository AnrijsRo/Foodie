package com.example.foodie.ui.composable.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.foodie.ui.theme.Padding

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    widthFraction: Float = 1f,
    height: Dp = 30.dp,
    radius: Dp = Padding.padding4,
    backGroundColor: Color = Color(0xFFC7C7CC)
) {
    Column(
        modifier
            .height(height)
            .fillMaxWidth(widthFraction)
            .shimmerOver()
            .clip(RoundedCornerShape(radius))
            .background(backGroundColor)
    ) {}
}