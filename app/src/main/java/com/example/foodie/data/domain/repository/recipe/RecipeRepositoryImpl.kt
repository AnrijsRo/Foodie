package com.example.foodie.data.domain.repository.recipe

import com.example.foodie.data.domain.persistent.FoodieDatabase
import com.example.foodie.data.domain.repository.recipe.data.RecipeDetails
import com.example.foodie.data.domain.repository.recipe.data.RecipeListing
import com.example.foodie.data.remote.api.RecipeApi
import com.example.foodie.util.OperationResult
import com.example.foodie.util.toOperationResult

class RecipeRepositoryImpl(
    private val recipeApi: RecipeApi,
    private val foodieDatabase: FoodieDatabase
) : RecipeRepository {

    override suspend fun getRecipeDetails(recipeId: Int): OperationResult<RecipeDetails> {
        return recipeApi.getRecipeDetails(recipeId = recipeId)
            .toOperationResult { it.toRecipeDetails() }
    }

    override suspend fun getRecipeList(
        offset: Int,
        numberOfItems: Int
    ): OperationResult<List<RecipeListing>> {
        return recipeApi.getRecipeList(itemOffset = offset, numberOfItems = numberOfItems)
            .toOperationResult { recipeListDTO -> recipeListDTO.results.map { it.toRecipeListing() } }
    }
}