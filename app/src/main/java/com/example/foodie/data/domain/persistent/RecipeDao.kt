package com.example.foodie.data.domain.persistent

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.foodie.data.domain.persistent.model.RecipeDetailsEntity
import com.example.foodie.data.domain.persistent.model.RecipeListingEntity

@Dao
abstract class RecipeDao {
    @Upsert
    internal abstract suspend fun saveRecipeListing(recipeList: List<RecipeListingEntity>)

    @Query("SELECT * FROM RecipeListingEntity")
    internal abstract suspend fun getSavedRecipeListings(): List<RecipeListingEntity>

    @Upsert
    internal abstract suspend fun saveRecipeDetails(recipeDetails: RecipeDetailsEntity)

    @Query("SELECT * FROM RecipeDetailsEntity WHERE id= :recipeId")
    internal abstract suspend fun getSavedRecipeDetails(recipeId: Int) :RecipeDetailsEntity?
}