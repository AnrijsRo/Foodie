package com.example.foodie.ui.presentation.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.domain.repository.recipe.RecipeRepository
import com.example.foodie.data.domain.repository.recipe.data.RecipeListing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val recipeRepository: RecipeRepository) :
    ViewModel() {
    var recipeList = mutableStateListOf<RecipeListing>()
        private set

    init {
        getRandomRecipes()
    }

    private fun getRandomRecipes() {
        viewModelScope.launch {
            recipeRepository.getRecipeList(offset = 0, numberOfItems = 5)
                .onSuccess { recipeList.addAll(it) }
                .onError { }
        }
    }
}