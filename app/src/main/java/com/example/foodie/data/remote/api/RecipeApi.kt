package com.example.foodie.data.remote.api

import com.example.foodie.data.remote.api.RecipeApi.Routes.RECIPES_DETAILS
import com.example.foodie.data.remote.api.RecipeApi.Routes.RECIPES_LIST
import com.example.foodie.data.remote.response.recipe.RecipeDetailsDTO
import com.example.foodie.data.remote.response.recipe.RecipeListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET(RECIPES_DETAILS)
    suspend fun getRecipeDetails(
        @Query("id") recipeId: Int,
    ): Response<RecipeDetailsDTO>

    @GET(RECIPES_LIST)
    suspend fun getRecipeList(
        @Query("from") itemOffset: Int,
        @Query("size") numberOfItems: Int
    ): Response<RecipeListDTO>

    object Routes {
        const val RECIPES_LIST = "recipes/list"
        const val RECIPES_DETAILS = "recipes/get-more-info"
    }
}