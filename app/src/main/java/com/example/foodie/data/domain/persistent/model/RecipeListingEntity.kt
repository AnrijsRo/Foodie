package com.example.foodie.data.domain.persistent.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodie.data.domain.repository.recipe.data.RecipeListing

@Entity
internal data class RecipeListingEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String
) {
    companion object {
        fun toEntity(recipeListing: RecipeListing): RecipeListingEntity {
            return RecipeListingEntity(
                id = recipeListing.id,
                name = recipeListing.name,
                description = recipeListing.description,
                imageUrl = recipeListing.imageUrl
            )
        }

        fun toDomain(recipeListing: RecipeListingEntity): RecipeListing {
            return RecipeListing(
                id = recipeListing.id,
                name = recipeListing.name,
                description = recipeListing.description,
                imageUrl = recipeListing.imageUrl
            )
        }
    }

}
