package com.example.recipeapp.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class RecipeDeserializer: JsonDeserializer<Recipe> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Recipe {
        val recipeObject = json?.getAsJsonObject()
        val title: String? = recipeObject?.get("recipe.label")?.asString
        val imageSource: String?  = recipeObject?.get("recipe.label")?.asString
        val calories: Double?  = recipeObject?.get("recipe.calories")?.asDouble
        val timeToMake: Double? = recipeObject?.get("recipe.totalTime")?.asDouble

        return Recipe(null,title,imageSource,calories,timeToMake)
    }
}