package com.example.recipeapp.Persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipeapp.model.Recipe

@Database(entities = [Recipe::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {

}