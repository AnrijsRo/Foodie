package com.example.foodie.data.domain.persistent.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class RecipeListingEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String
)
