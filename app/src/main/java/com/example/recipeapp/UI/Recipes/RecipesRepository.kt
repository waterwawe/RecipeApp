package com.example.recipeapp.UI.Recipes

import com.example.recipeapp.Network.RecipeService
import com.example.recipeapp.Persistance.RecipeDao
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val recipeService: RecipeService,
    private val recipeDao: RecipeDao
){
}