package com.example.foodie.data.domain.repository.recipe

import com.example.foodie.data.domain.repository.recipe.data.RecipeDetails
import com.example.foodie.data.domain.repository.recipe.data.RecipeListing
import com.example.foodie.util.OperationResult

interface RecipeRepository {
    suspend fun getRecipeDetails(recipeId: Int): OperationResult<RecipeDetails>
    suspend fun getRecipeList(offset :Int, numberOfItems :Int):  OperationResult<List<RecipeListing>>
}