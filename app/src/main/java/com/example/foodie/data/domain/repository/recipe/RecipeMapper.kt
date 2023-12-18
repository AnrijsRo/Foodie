package com.example.foodie.data.domain.repository.recipe

import com.example.foodie.data.domain.persistent.model.RecipeListingEntity
import com.example.foodie.data.domain.repository.recipe.data.RecipeDetails
import com.example.foodie.data.domain.repository.recipe.data.RecipeListing
import com.example.foodie.data.remote.response.recipe.RecipeDetailsDTO
import com.example.foodie.data.remote.response.recipe.RecipeListingDTO

fun RecipeListingDTO.toRecipeListing(): RecipeListing {
    return RecipeListing(id = id, name = name, description = description, imageUrl = thumbnail_url)
}

fun RecipeDetailsDTO.toRecipeDetails(): RecipeDetails {
    return RecipeDetails(id = id, name = name, description = description, imageUrl = thumbnail_url)
}

internal fun RecipeListing.toRecipeListingEntity(): RecipeListingEntity {
    return RecipeListingEntity(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl
    )
}

internal fun RecipeListingEntity.toRecipeListing(): RecipeListing {
    return RecipeListing(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl
    )
}