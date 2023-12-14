package com.example.foodie.data.domain.repository.recipe

import com.example.foodie.data.domain.repository.recipe.data.RecipeListing
import com.example.foodie.data.remote.api.RecipeApi
import com.example.foodie.util.OperationResult
import com.example.foodie.util.toOperationResult

class RecipeRepositoryImpl(private val recipeApi: RecipeApi) : RecipeRepository {
    override suspend fun getRecipeDetails(): String {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeList(
        offset: Int,
        numberOfItems: Int
    ): OperationResult<List<RecipeListing>> {
        return recipeApi.getRecipeList(itemOffset = offset, numberOfItems = numberOfItems)
            .toOperationResult { recipeListDTO -> recipeListDTO.results.map { it.toRecipeListing() } }
    }
}