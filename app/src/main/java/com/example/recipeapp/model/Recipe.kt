package com.example.recipeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey val id: Long,
    val title: String,
    val imageSource: String,
    val calories: Number,
    val timeToMake: Number
    )