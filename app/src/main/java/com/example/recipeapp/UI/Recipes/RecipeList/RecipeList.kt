package com.example.recipeapp.UI.Recipes.RecipeList


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.UI.Recipes.RecipeFragment.RecipeCard
import com.example.recipeapp.model.Recipe
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun RecipeList(
    modifier: Modifier = Modifier,
    recipes: List<Recipe>,
    isFavourites: Boolean,
    onSave: (recipe: Recipe) -> Unit
){
    LazyColumn {
        items(recipes) { recipe ->
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {
                Card(
                    modifier = Modifier.padding(4.dp),
                    backgroundColor = Color.LightGray
                ) {
                    Column(horizontalAlignment =Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)) {
                        RecipeCard(modifier, recipe)
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Button(
                            onClick = { onSave(recipe) })
                        {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Favorite",
                                modifier = Modifier.size(ButtonDefaults.IconSize)
                            )
                            Text(text = if (isFavourites) {"Remove from favourites"} else "Add to Favourites")
                        }
                    }
                }
            }
        }
    }
}