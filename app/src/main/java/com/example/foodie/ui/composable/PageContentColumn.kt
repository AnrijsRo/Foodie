package com.example.foodie.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.foodie.R
import com.example.foodie.ui.theme.Padding
import com.example.foodie.ui.theme.FoodieStyle
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun PageContentColumn(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    horizontalPadding: Dp = Padding.padding3,
    verticalPadding: Dp = Padding.padding2,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    pageTitle: String? = null,
    hasBackButton: Boolean = true,
    onBackButtonClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier.fillMaxWidth()) {
        pageTitle?.let {
            TopBar(
                title = pageTitle,
                horizontalPadding = horizontalPadding,
                onBackButtonClicked = { onBackButtonClick?.invoke() ?: navigator.popBackStack() },
                hasBackButton = hasBackButton
            )
        }
        Column(
            modifier = Modifier.padding(horizontal = horizontalPadding, vertical = verticalPadding),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            content.invoke(this)
        }
    }


}

@Composable
private fun TopBar(
    title: String,
    horizontalPadding: Dp,
    hasBackButton: Boolean,
    onBackButtonClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(
                top = Padding.padding7,
                bottom = Padding.padding5,
                start = horizontalPadding,
                end = horizontalPadding
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        if (hasBackButton) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = "Back button",
                Modifier
                    .clickable(interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = { onBackButtonClicked() })
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = Padding.padding7),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = FoodieStyle.bold19Black,
            )
        }
    }
}
