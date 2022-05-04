package com.example.recipeapp.UI.Recipes

import androidx.annotation.WorkerThread
import com.example.recipeapp.Network.RecipeService
import com.example.recipeapp.Persistance.RecipeDao
import com.example.recipeapp.model.Recipe
import kotlinx.coroutines.Dispatchers
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
        queryString: String
    ) = flow {
        var queryMap = HashMap<String,String>()
        queryMap.put("q",queryString)
        val recipes: List<Recipe> = recipeService.searchRecipes(queryMap).execute().body()
            emit(recipes)
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun loadFavouriteRecipes(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
    ) = flow {
        val recipes: List<Recipe> = recipeDao.getRecipeList()
        emit(recipes)
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)
}