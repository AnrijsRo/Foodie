package com.example.foodie.ui.presentation.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.domain.repository.recipe.RecipeRepository
import com.example.foodie.data.domain.repository.recipe.data.RecipeListing
import com.example.foodie.ui.presentation.details.RecipeDetailsNavArgs
import com.example.foodie.ui.presentation.home.HomePageNavigationEvent.NavigateToRecipeDetailsPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val recipeRepository: RecipeRepository) :
    ViewModel() {
    private val _navigatorFlow = MutableSharedFlow<NavigateToRecipeDetailsPage>()

    val navigationEventFlow = _navigatorFlow.asSharedFlow()
    var recipeList = mutableStateListOf<RecipeListing>()
        private set

    init {
        getRandomRecipes()
    }

    private fun getRandomRecipes() {
        viewModelScope.launch {
            recipeRepository.getRecipeList(offset = 0, numberOfItems = 20)
                .onSuccess { recipeList.addAll(it) }
                .onError { }
        }
    }

    fun onRecipeClicked(recipe: RecipeListing) {
        viewModelScope.launch {
            _navigatorFlow.emit(NavigateToRecipeDetailsPage(RecipeDetailsNavArgs(recipe.id)))
        }
    }
}

sealed class HomePageNavigationEvent {
    data class NavigateToRecipeDetailsPage(
        val navArgs: RecipeDetailsNavArgs
    ) : HomePageNavigationEvent()
}