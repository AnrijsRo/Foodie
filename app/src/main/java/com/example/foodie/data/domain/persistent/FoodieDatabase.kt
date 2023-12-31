package com.example.foodie.data.domain.persistent

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodie.data.domain.persistent.model.RecipeDetailsEntity
import com.example.foodie.data.domain.persistent.model.RecipeListingEntity

const val FOODIE_DATABASE = "foodie_db"

@Database(
    entities = [RecipeListingEntity::class, RecipeDetailsEntity::class],
    version = 2,
    exportSchema = false
)
abstract class FoodieDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}