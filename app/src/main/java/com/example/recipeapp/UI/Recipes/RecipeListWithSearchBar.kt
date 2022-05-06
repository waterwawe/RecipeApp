package com.example.recipeapp.UI.Recipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.recipeapp.UI.Recipes.RecipeList.RecipeList
import com.example.recipeapp.model.Recipe

@Composable
fun RecipeListWithSearchBar(
    modifier: Modifier = Modifier,
    recipes: List<Recipe>,
    onSave: (recipe: Recipe) -> Unit,
    onSearch: (queryString: String) -> Unit
){
    val textState = remember { mutableStateOf(TextFieldValue("")) }
Column(modifier = modifier
    .padding(3.dp)
    .fillMaxWidth()){
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(3.dp)){
        TextField(
        modifier= modifier.padding(5.dp),
        value = textState.value,
        onValueChange = { value -> textState.value = value })
        Button(
            onClick = { onSearch(textState.value.text)},
            ) {
           Text("Search")
        }}
    RecipeList(modifier,recipes,false,onSave)
    }
}