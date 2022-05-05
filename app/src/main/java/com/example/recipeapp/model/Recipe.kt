package com.example.recipeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String?,
    val imageSource: String?,
    val calories: Double?,
    val timeToMake: Double?
    )