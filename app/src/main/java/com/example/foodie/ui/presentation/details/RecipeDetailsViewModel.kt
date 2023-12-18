package com.example.foodie.ui.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.domain.repository.recipe.RecipeRepository
import com.example.foodie.data.domain.repository.recipe.data.RecipeDetails
import com.example.foodie.ui.presentation.navArgs
import com.example.foodie.util.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private val navArgs = savedStateHandle.navArgs<RecipeDetailsNavArgs>()
    var recipeDetails by mutableStateOf<RecipeDetails?>(null)
        private set
    var isLoading by mutableStateOf(false)
        private set

    init {
        getRecipeDetails(navArgs.recipeId)
    }

    private fun getRecipeDetails(recipeId: Int) = viewModelScope.launch {
        isLoading = true
        recipeRepository.getRecipeDetails(recipeId)
            .onSuccess { handleReceivedRecipeDetails(it) }
        isLoading = false
    }

    private fun handleReceivedRecipeDetails(details: RecipeDetails) {
        recipeDetails = details
        launch {
            recipeRepository.saveRecipeDetails(details)
        }
    }
}
