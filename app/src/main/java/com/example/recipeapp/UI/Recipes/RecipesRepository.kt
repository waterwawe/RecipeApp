package com.example.recipeapp.UI.Recipes

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.recipeapp.Network.RecipeService
import com.example.recipeapp.Persistance.RecipeDao
import androidx.lifecycle.lifecycleScope
import com.example.recipeapp.Persistance.AppDatabase
import com.example.recipeapp.model.DataResponse
import kotlinx.coroutines.launch
import com.example.recipeapp.model.Recipe
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val recipeService: RecipeService,
    private val recipeDao: RecipeDao
){
    @WorkerThread
    suspend fun loadRecipes(queryString: String): List<Recipe> {
        var queryMap = HashMap<String,String>()
        queryMap.put("q",queryString)
        queryMap.put("type","public")
        queryMap.put("app_id","c0857fc8")
        queryMap.put("app_key", "8a3d7f628aee1f9abc6077eb28708fb4")
        var result = listOf<Recipe>()
        val callResult = recipeService.searchRecipes(queryMap)
        callResult.suspendOnSuccess {
            result = data.hits
        }

        return result;
    }

    @WorkerThread
    suspend fun addToFavourites(recipe: Recipe)
    {
        recipeDao.insertRecipe(recipe)
        Log.i("Added recipe",recipe?.title.toString())
    }

    @WorkerThread
    suspend fun deleteFromFavourites(recipe: Recipe)
    {
        recipeDao.deleteRecipe(recipe)
        Log.i("Deleted recipe",recipe?.title.toString())
    }

    @WorkerThread
    suspend fun getFavourites(): List<Recipe> {
        return recipeDao.getRecipeList();
    }
}