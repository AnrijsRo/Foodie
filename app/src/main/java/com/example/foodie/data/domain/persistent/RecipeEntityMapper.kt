package com.example.foodie.data.domain.persistent

import com.example.foodie.data.domain.persistent.model.RecipeDetailsEntity
import com.example.foodie.data.domain.persistent.model.RecipeListingEntity
import com.example.foodie.data.domain.repository.recipe.data.RecipeDetails
import com.example.foodie.data.domain.repository.recipe.data.RecipeListing

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

internal fun RecipeDetails.toRecipeDetailsEntity(): RecipeDetailsEntity {
    return RecipeDetailsEntity(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl
    )
}

internal fun RecipeDetailsEntity.toRecipeDetails(): RecipeDetails {
    return RecipeDetails(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl
    )
}
