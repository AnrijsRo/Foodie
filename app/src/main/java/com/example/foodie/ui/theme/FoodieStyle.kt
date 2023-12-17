package com.example.foodie.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.example.foodie.R

object FoodieStyle {
    private val Bull = FontFamily(
        Font(R.font.bull_thin1, weight = FontWeight.Thin),
        Font(R.font.bull_regular, weight = FontWeight.Normal),
        Font(R.font.bull_medium, weight = FontWeight.Medium),
        Font(R.font.bull_bold, weight = FontWeight.Bold),
        Font(R.font.bull_heavy1, weight = FontWeight.ExtraBold),
    )

    val regular14White = TextStyle(
        color = Color.White,
        fontFamily = Bull,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp,
    )

    val bold19White = TextStyle(
        color = Color.White,
        fontFamily = Bull,
        fontWeight = FontWeight.Bold,
        fontSize = 19.sp
    )
    val bold19Black = TextStyle(
        color = Color.Black,
        fontFamily = Bull,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        ),
        fontWeight = FontWeight.Bold,
        fontSize = 19.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Proportional,
            trim = LineHeightStyle.Trim.None
        ),
    )

    val medium16Black = TextStyle(
        color = Color.Black,
        fontFamily = Bull,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp
    )
    val medium16White = TextStyle(
        color = Color.White,
        fontFamily = Bull,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp
    )

    val thin48White = TextStyle(
        color = Color.White,
        fontFamily = Bull,
        fontWeight = FontWeight.Thin,
        fontSize = 48.sp
    )
}