package com.example.recipeapp.network

import com.example.recipeapp.model.Recipe
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RecipeService {

    @GET("recipes")
    suspend fun fetchRecipesList(@QueryMap options:Map<String, String>): Call<List<Recipe>>
}