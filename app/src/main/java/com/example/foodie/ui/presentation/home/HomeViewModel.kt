package com.example.foodie.ui.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.domain.repository.recipe.RecipeRepository
import com.example.foodie.data.domain.repository.recipe.data.RecipeListing
import com.example.foodie.ui.presentation.details.RecipeDetailsNavArgs
import com.example.foodie.ui.presentation.home.HomePageNavigationEvent.NavigateToRecipeDetailsPage
import com.example.foodie.util.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(private val recipeRepository: RecipeRepository) :
    ViewModel() {
    private val _navigatorFlow = MutableSharedFlow<NavigateToRecipeDetailsPage>()

    val navigationEventFlow = _navigatorFlow.asSharedFlow()
    var recipeList by mutableStateOf<List<RecipeListing>>(emptyList())
        private set

    init {
        getRandomRecipes(true)
    }

    fun onRecipeClicked(recipe: RecipeListing) {
        viewModelScope.launch {
            _navigatorFlow.emit(NavigateToRecipeDetailsPage(RecipeDetailsNavArgs(recipe.id)))
        }
    }

    fun onRefresh() {
        getRandomRecipes(false)
    }

    private fun getRandomRecipes(fromCache: Boolean) {
        val offset = Random.nextInt(0, 100)

        launch {
            recipeRepository.getRecipeList(
                offset = offset,
                numberOfItems = 20,
                fromCache = fromCache
            )
                .onSuccess { handleReceivedRecipes(it) }
                .onError { }
        }
    }

    private fun handleReceivedRecipes(recipes: List<RecipeListing>) {
        recipeList = recipes
        launch {
            recipeRepository.saveRecipeListing(recipes)
        }
    }
}

sealed class HomePageNavigationEvent {
    data class NavigateToRecipeDetailsPage(
        val navArgs: RecipeDetailsNavArgs
    ) : HomePageNavigationEvent()
}