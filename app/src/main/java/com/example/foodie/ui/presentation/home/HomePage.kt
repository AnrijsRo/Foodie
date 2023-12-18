package com.example.foodie.ui.presentation.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.Glide
import com.example.foodie.R
import com.example.foodie.data.domain.repository.recipe.data.RecipeListing
import com.example.foodie.ui.composable.VerticalSpacer
import com.example.foodie.ui.composable.collectAsEffect
import com.example.foodie.ui.composable.conditional
import com.example.foodie.ui.composable.container.PageContentColumn
import com.example.foodie.ui.composable.shimmer.shimmerOver
import com.example.foodie.ui.presentation.destinations.RecipeDetailsPageDestination
import com.example.foodie.ui.presentation.home.HomeViewModel.HomePageNavigationEvent
import com.example.foodie.ui.presentation.home.HomeViewModel.HomePageNavigationEvent.NavigateToRecipeDetailsPage
import com.example.foodie.ui.theme.FoodieStyle
import com.example.foodie.ui.theme.Padding
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.SharedFlow

@OptIn(ExperimentalMaterialApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun HomePage(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: DestinationsNavigator = EmptyDestinationsNavigator
) {

    val pullRefreshState =
        rememberPullRefreshState(refreshing = viewModel.refreshState.isRefreshing(),
            refreshingOffset = 100.dp,
            onRefresh = { viewModel.onRefresh() })

    HandleNavigationEvent(navigationEvent = viewModel.navigationEventFlow, navigator = navigator)
    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
        PageContentColumn(
            navigator = navigator,
            hasBackButton = false,
            pageTitle = stringResource(id = R.string.home_page_title)
        ) {
            AnimatedContent(targetState = viewModel.isLoading, label = "") { isLoading ->
                if (isLoading) {
                    RecipeListingShimmer()
                } else {
                    RecipeListingLazyColumn(
                        recipeList = viewModel.recipeList,
                        isRefreshing = viewModel.refreshState.isRefreshing(),
                        onRecipeClicked = { viewModel.onRecipeClicked(it) }
                    )
                }
            }
        }
        PullRefreshIndicator(
            refreshing = viewModel.refreshState.isRefreshing(),
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun RecipeListingLazyColumn(
    recipeList: List<RecipeListing>,
    isRefreshing: Boolean,
    onRecipeClicked: (RecipeListing) -> Unit,
) {
    LazyColumn(state = rememberLazyListState()) {
        item { VerticalSpacer(height = Padding.padding8) }
        items(items = recipeList, key = { it.id }) { recipe ->
            RecipeListItem(
                recipeListing = recipe,
                isRefreshing = isRefreshing,
                onRecipeClicked = { onRecipeClicked(recipe) })
            VerticalSpacer(height = Padding.padding3)
        }
    }

}

@Composable
private fun RecipeListItem(
    recipeListing: RecipeListing,
    isRefreshing: Boolean,
    onRecipeClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Padding.padding4))
            .aspectRatio(1f)
            .clickable { onRecipeClicked() }
            .conditional(isRefreshing) { shimmerOver() },
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
            is NavigateToRecipeDetailsPage -> navigator.navigate(
                RecipeDetailsPageDestination(it.navArgs),
                onlyIfResumed = true
            )
        }
    }
}

@Composable
private fun RecipeListingShimmer() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        repeat(5) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(Padding.padding4))
                    .shimmerOver()

                    .background(Color(0xFFC7C7CC))

            )
            VerticalSpacer(height = Padding.padding3)
        }

    }
}

@Preview
@Composable
private fun PreviewRecipeListItem() {
    RecipeListItem(
        recipeListing = RecipeListing(
            id = 2498,
            name = "Dominic Kelly",
            description = "prodesset",
            imageUrl = "https://search.yahoo.com/search?p=dictas",
        ), onRecipeClicked = { }, isRefreshing = false
    )
}
