package com.example.recipeapp.UI.Recipes.About

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.recipeapp.UI.Recipes.RecipesViewModel

@Composable
fun About() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)) {
        Text("This application is created by Drexler Nándor, student of the Budapest University of Technology and Economics for the subject Mobile software laboratory. Feel free to do anything with it.")
    }
}