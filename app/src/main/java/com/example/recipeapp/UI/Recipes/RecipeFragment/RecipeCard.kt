package com.example.recipeapp.UI.Recipes.RecipeFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.example.recipeapp.R
import com.example.recipeapp.model.Recipe
import kotlinx.coroutines.NonDisposableHandle.parent
import java.math.RoundingMode

@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    recipe: Recipe,
) {
    Surface(
        modifier = modifier
            .padding(4.dp),
        color = MaterialTheme.colors.onBackground,
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally){
        Row {

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = recipe?.title ?: "",
                style = MaterialTheme.typography.h6,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
        Row {
            Image(
                painter = rememberAsyncImagePainter(recipe?.imageSource),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )
        }
        Row {
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 12.dp),
                text = "Calories: "+recipe.calories?.toBigDecimal()?.setScale(2, RoundingMode.UP)?.toDouble().toString(),
                style = MaterialTheme.typography.body1,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
        Row{
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 12.dp),
                text = "Time to make: "+recipe.timeToMake.toString(),
                style = MaterialTheme.typography.body1,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
        }
    }
}