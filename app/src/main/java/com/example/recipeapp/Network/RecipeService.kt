package com.example.recipeapp.Network

import com.example.recipeapp.model.DataResponse
import com.example.recipeapp.model.Recipe
import com.skydoves.sandwich.ApiResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RecipeService {

    @GET("recipes")
    suspend fun searchRecipes(@QueryMap options:Map<String, String>): ApiResponse<DataResponse>

    @GET("favrecipes")
    suspend fun getFavouriteRecipes(): ApiResponse<List<Recipe>>

    @PUT("favrecipes")
    fun update(@Body recipe: Recipe): ApiResponse<ResponseBody>

    @DELETE("favrecipes/{id}")
    fun delete(@Path("id") id: Long?): ApiResponse<ResponseBody>
}