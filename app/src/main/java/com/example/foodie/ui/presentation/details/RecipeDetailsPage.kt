package com.example.foodie.ui.presentation.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodie.data.domain.repository.recipe.data.RecipeDetails
import com.example.foodie.ui.composable.FoodieImage
import com.example.foodie.ui.composable.PageContentColumn
import com.example.foodie.ui.composable.VerticalSpacer
import com.example.foodie.ui.theme.Padding
import com.example.foodie.ui.theme.FoodieStyle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator


@Destination(navArgsDelegate = RecipeDetailsNavArgs::class)
@Composable
fun RecipeDetailsPage(
    viewModel: RecipeDetailsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator = EmptyDestinationsNavigator
) {
    PageContentColumn(
        navigator = navigator,
        horizontalPadding = 0.dp,
        hasBackButton = true,
        verticalPadding = 0.dp
    ) {
        AnimatedContent(targetState = viewModel.isLoading, label = "main") { isLoading ->
            if (isLoading) {
                RecipeDetailsShimmer()
            } else {
                RecipeDetailsContent(viewModel.recipeDetails)
            }
        }
    }
}

@Composable
fun RecipeDetailsContent(recipeDetails: RecipeDetails?) {
    recipeDetails?.let { details ->
        Column(Modifier.verticalScroll(rememberScrollState())) {
            FoodieImage(
                imageUrl = details.imageUrl,
                modifier = Modifier.aspectRatio(1f)
            )
            Column(Modifier.padding(horizontal = Padding.padding4)) {

                VerticalSpacer(height = Padding.padding3)
                Text(text = details.name, style = FoodieStyle.bold19Black)
                VerticalSpacer(height = Padding.padding5)
                Text(text = details.description, style = FoodieStyle.medium16Black)


            }
        }

    }
}

@Composable
private fun RecipeDetailsShimmer() {


}
