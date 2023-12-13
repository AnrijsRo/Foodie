package com.example.foodie.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.foodie.ui.theme.Padding
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun PageContentColumn(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    padding: PaddingValues = PaddingValues(
        horizontal = Padding.padding3,
        vertical = Padding.padding5
    ),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    pageTitle: String? = null,
    onBackButtonClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier
            .fillMaxWidth()
    ) {
        pageTitle?.let {
            TopBar(
                title = pageTitle,
                horizontalPadding = Padding.padding4,
                onBackButtonClicked = { onBackButtonClick?.invoke() ?: navigator.popBackStack() }
            )
        }
        Column(
            modifier = Modifier.padding(padding),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            content.invoke(this)
        }
    }


}

@Composable
fun TopBar(
    title: String,
    horizontalPadding: Dp,
    onBackButtonClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(
                top = Padding.padding7,
                bottom = Padding.padding7,
                start = horizontalPadding,
                end = horizontalPadding
            ),
        contentAlignment = Alignment.CenterStart
    ) {
//            Icon(
//                painter = painterResource(R.),
//                contentDescription = "Back button",
//                Modifier
//                    .clickable(interactionSource = remember { MutableInteractionSource() },
//                        indication = rememberRipple(bounded = false),
//                        onClick = { onBackButtonClicked() })
//            )
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
            )
        }
    }
}
