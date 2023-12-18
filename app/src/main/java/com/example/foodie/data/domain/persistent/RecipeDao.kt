package com.example.foodie.data.domain.persistent

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.foodie.data.domain.persistent.model.RecipeListingEntity

@Dao
abstract class RecipeDao {
    @Upsert
    internal abstract suspend fun upsertRecipeListing(recipeList: List<RecipeListingEntity>)

    @Query("SELECT * FROM RecipeListingEntity")
    internal abstract suspend fun getSavedRecipeListings(): List<RecipeListingEntity>
}