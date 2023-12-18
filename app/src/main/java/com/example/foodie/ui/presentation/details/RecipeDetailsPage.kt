package com.example.foodie.ui.presentation.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodie.data.domain.repository.recipe.data.RecipeDetails
import com.example.foodie.ui.composable.VerticalSpacer
import com.example.foodie.ui.composable.container.PageContentColumn
import com.example.foodie.ui.composable.image.FoodieImage
import com.example.foodie.ui.composable.shimmer.ShimmerBox
import com.example.foodie.ui.composable.shimmer.shimmerOver
import com.example.foodie.ui.theme.FoodieStyle
import com.example.foodie.ui.theme.Padding
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
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(Padding.padding4))
                .shimmerOver()
                .background(Color(0xFFC7C7CC))
        )
        VerticalSpacer(height = Padding.padding3)
        ShimmerBox()
        VerticalSpacer(height = Padding.padding5)
        ShimmerBox(widthFraction = 0.7f, height = Padding.padding12)
    }


}
