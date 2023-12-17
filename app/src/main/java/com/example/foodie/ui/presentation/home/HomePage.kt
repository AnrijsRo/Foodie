package com.example.foodie.ui.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.Glide
import com.example.foodie.R
import com.example.foodie.data.domain.repository.recipe.data.RecipeListing
import com.example.foodie.ui.composable.PageContentColumn
import com.example.foodie.ui.composable.VerticalSpacer
import com.example.foodie.ui.composable.collectAsEffect
import com.example.foodie.ui.presentation.destinations.RecipeDetailsPageDestination
import com.example.foodie.ui.presentation.home.HomePageNavigationEvent.NavigateToRecipeDetailsPage
import com.example.foodie.ui.theme.Padding
import com.example.foodie.ui.theme.FoodieStyle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.SharedFlow

@RootNavGraph(start = true)
@Destination
@Composable
fun HomePage(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: DestinationsNavigator = EmptyDestinationsNavigator
) {
    HandleNavigationEvent(navigationEvent = viewModel.navigationEventFlow, navigator = navigator)
    PageContentColumn(
        navigator = navigator,
        hasBackButton = false,
        pageTitle = stringResource(id = R.string.home_page_title)
    ) {
        LazyColumn(state = rememberLazyListState()) {
            item { VerticalSpacer(height = Padding.padding8) }
            items(items = viewModel.recipeList, key = { it.id }) { recipe ->
                RecipeListItem(
                    recipeListing = recipe,
                    onRecipeClicked = { viewModel.onRecipeClicked(recipe) })
                VerticalSpacer(height = Padding.padding3)
            }
        }
    }
}

@Composable
fun RecipeListItem(
    recipeListing: RecipeListing,
    onRecipeClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Padding.padding4))
            .aspectRatio(1f)
            .clickable { onRecipeClicked() },
        contentAlignment = Alignment.BottomStart
    ) {
        GlideImage(imageModel = recipeListing.imageUrl,
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
        RecipeListingGradient()
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = Padding.padding3, vertical = Padding.padding4),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = recipeListing.name, style = FoodieStyle.bold19White)
            VerticalSpacer(height = Padding.padding3)
            Text(text = recipeListing.description, style = FoodieStyle.medium16White)
        }
    }

}

@Composable
private fun RecipeListingGradient() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.25f))
            .background(
                Brush.verticalGradient(
                    0.4F to Color.Black.copy(alpha = 0.01f),
                    0.90f to Color.Black.copy(alpha = 0.5f)
                )
            )
    )
}

@Composable
private fun HandleNavigationEvent(
    navigationEvent: SharedFlow<HomePageNavigationEvent>, navigator: DestinationsNavigator
) {
    navigationEvent.collectAsEffect {
        when (it) {
            is NavigateToRecipeDetailsPage -> navigator.navigate(RecipeDetailsPageDestination(it.navArgs))
        }
    }
}
