package com.example.recipeapp.Persistance

import androidx.room.*
import com.example.recipeapp.model.Recipe

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("SELECT * FROM Recipe")
    suspend fun getRecipeList(): List<Recipe>

}