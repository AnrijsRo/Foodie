package com.example.foodie.ui.presentation.details

import androidx.compose.runtime.Composable
import com.example.foodie.ui.composable.PageContentColumn
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator


@Destination
@Composable
fun RecipeDetailsPage(navigator: DestinationsNavigator = EmptyDestinationsNavigator) {
    PageContentColumn(navigator = navigator) {

    }
}