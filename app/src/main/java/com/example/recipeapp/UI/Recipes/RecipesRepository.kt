package com.example.recipeapp.UI.Recipes

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.recipeapp.Network.RecipeService
import com.example.recipeapp.Persistance.RecipeDao
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.recipeapp.model.Recipe
import com.skydoves.sandwich.onFailure
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
    fun loadRecipes(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit,
        queryString: String
    ) = flow {
        var queryMap = HashMap<String,String>()
        queryMap.put("q",queryString)
        queryMap.put("app_id","c0857fc")
        queryMap.put("app_key", "8a3d7f628aee1f9abc6077eb28708fb4")
        recipeService.searchRecipes(queryMap).suspendOnSuccess {
            emit(data.hits)
            Log.i("Recipe Repository:","API Called")
        }.onFailure { onError(this) }
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun loadFavouriteRecipes(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
    ) = flow {
        val recipes: List<Recipe> = recipeDao.getRecipeList()
        emit(recipes)
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

    fun addToFavourites(recipe: Recipe)
    {
        GlobalScope.launch{
            recipeDao.insertRecipe(recipe)
        }
    }
}