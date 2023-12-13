package com.example.foodie.data.domain.repository.recipe

interface RecipeRepository {
    suspend fun getRecipeDetails(): String
    suspend fun getRecipeList(): String
}