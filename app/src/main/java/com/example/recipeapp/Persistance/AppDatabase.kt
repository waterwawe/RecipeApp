package com.example.recipeapp.Persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipeapp.model.Recipe

@Database(entities = [Recipe::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
}