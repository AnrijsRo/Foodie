package com.example.foodie.data.domain.repository.recipe

import com.example.foodie.data.remote.api.RecipeApi

class RecipeRepositoryImpl(private val recipeApi: RecipeApi) :RecipeRepository {
    override suspend fun getRecipeDetails(): String {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeList(): String {
        TODO("Not yet implemented")
    }
}