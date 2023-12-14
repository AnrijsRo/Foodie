package com.example.foodie.data.domain.repository.recipe

import com.example.foodie.data.domain.repository.recipe.data.RecipeListing
import com.example.foodie.data.remote.response.RecipeListingDTO

fun RecipeListingDTO.toRecipeListing(): RecipeListing {
    return RecipeListing(name = name, description = description, imageUrl = thumbnail_url)
}