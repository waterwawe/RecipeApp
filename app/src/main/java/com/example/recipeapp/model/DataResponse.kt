package com.example.recipeapp.model

data class DataResponse(
    val from:Int,
    val to: Int,
    val count: Int,
    val hits:List<Recipe>
)
