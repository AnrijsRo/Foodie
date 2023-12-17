package com.example.foodie.data.remote.response.recipe

data class RecipeDetailsDTO(
    val id :Int,
    val name: String,
    val description: String,
    val thumbnail_url: String
)
