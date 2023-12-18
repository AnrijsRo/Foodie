package com.example.foodie.data.domain.repository.recipe

import com.example.foodie.data.domain.persistent.RecipeDao
import com.example.foodie.data.domain.repository.recipe.data.RecipeDetails
import com.example.foodie.data.domain.repository.recipe.data.RecipeListing
import com.example.foodie.data.remote.api.RecipeApi
import com.example.foodie.util.OperationResult
import com.example.foodie.util.toOperationResult

class RecipeRepositoryImpl(
    private val recipeApi: RecipeApi,
    private val recipeDao: RecipeDao
) : RecipeRepository {

    override suspend fun getRecipeDetails(recipeId: Int): OperationResult<RecipeDetails> {
        return recipeApi.getRecipeDetails(recipeId = recipeId)
            .toOperationResult { it.toRecipeDetails() }
    }

    override suspend fun getRecipeList(
        offset: Int, numberOfItems: Int, fromCache: Boolean
    ): OperationResult<List<RecipeListing>> {
        val listings =
            if (fromCache) recipeDao.getSavedRecipeListings()
                .map { it.toRecipeListing() } else emptyList()

        return if (listings.isNotEmpty()) {
            OperationResult.Success(listings)
        } else {
            recipeApi.getRecipeList(itemOffset = offset, numberOfItems = numberOfItems)
                .toOperationResult { recipeListDTO ->
                    recipeListDTO.results.map {
                        it.toRecipeListing()
                    }
                }
        }
    }

    override suspend fun saveRecipeListing(recipes: List<RecipeListing>) {
        recipeDao.upsertRecipeListing(recipes.map { it.toRecipeListingEntity() })
    }
}