package com.example.recipeapp.Network

import com.example.recipeapp.model.Recipe
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RecipeService {

    @GET("recipes")
    suspend fun searchRecipes(@QueryMap options:Map<String, String>): Call<List<Recipe>>

    @GET("favrecipes")
    suspend fun getFavouriteRecipes(): Call<List<Recipe>>

    @PUT("favrecipes")
    fun update(@Body recipe: Recipe): Call<ResponseBody>

    @DELETE("favrecipes/{id}")
    fun delete(@Path("id") id: Long?): Call<ResponseBody>
}