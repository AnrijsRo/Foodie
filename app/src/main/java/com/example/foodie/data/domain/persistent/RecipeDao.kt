package com.example.foodie.data.domain.persistent

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.foodie.data.domain.persistent.model.RecipeListingEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RecipeDao {
    @Upsert
    internal abstract suspend fun insertRecipe(notifications: RecipeListingEntity)

    @Query("SELECT * FROM RecipeListingEntity")
    internal abstract fun getAvailableCampaignProgressFlow(): Flow<List<RecipeListingEntity>>
}