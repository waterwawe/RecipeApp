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
        val recipe = recipeObject?.getAsJsonObject("recipe")
        val images = recipe?.getAsJsonObject("images")
        val thumbnail = images?.getAsJsonObject("THUMBNAIL")
        val title: String? = recipe?.get("label")?.asString
        val imageSource: String? = thumbnail?.get("url")?.asString
        val calories: Double?  = recipe?.get("calories")?.asDouble
        val timeToMake: Double? = recipe?.get("totalTime")?.asDouble

        return Recipe(null,title,imageSource,calories,timeToMake)
    }
}