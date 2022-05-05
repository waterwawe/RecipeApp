package com.example.recipeapp.UI.Recipes

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Observer
import com.example.recipeapp.Extensions.visible
import com.example.recipeapp.UI.Recipes.About.About
import com.example.recipeapp.UI.Recipes.RecipeList.RecipeList
import com.example.recipeapp.model.Recipe
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun Recipes(viewModel: RecipesViewModel) {
    val navController = rememberNavController()
    val recipes by viewModel.recipeList.observeAsState()
    val favourites by viewModel.favourites.observeAsState()
    val isLoading: Boolean by viewModel.isLoading
    val selectedTab = RecipesTab.getTabFromResource(viewModel.selectedTab.value)
    val tabs = RecipesTab.values()

    ProvideWindowInsets {
        NavHost(navController = navController, startDestination = NavScreen.Home.route) {
            composable(NavScreen.Home.route) {
                ConstraintLayout {
                    val (body, progress) = createRefs()
                    Scaffold(
                        backgroundColor = Color.White,
                        topBar = { RecipeAppBar() },
                        modifier = Modifier.constrainAs(body) {
                            top.linkTo(parent.top)
                        },
                        bottomBar = {
                            BottomNavigation(
                                backgroundColor = Color.Blue,
                                modifier = Modifier
                                    .navigationBarsHeight(56.dp)
                            ) {
                                tabs.forEach { tab ->
                                    BottomNavigationItem(
                                        icon = {Icons.Filled.Home},
                                        label = {
                                            Text(
                                                text = tab.title,
                                                color = Color.White
                                            )
                                        },
                                        selected = tab == selectedTab,
                                        onClick = { viewModel.selectTab(tab.ordinal) },
                                        selectedContentColor = LocalContentColor.current,
                                        unselectedContentColor = LocalContentColor.current,
                                        modifier = Modifier.navigationBarsPadding()
                                    )
                                }
                            }
                        }
                    ) {innerPadding ->
                    val modifier = Modifier.padding(innerPadding)
                    Crossfade(selectedTab) { destination ->
                        when (destination) {
                            RecipesTab.RECIPES -> RecipeList(modifier, recipes ?: listOf(), {recipe -> viewModel.saveToFavourites(recipe)})
                            RecipesTab.FAVOURITES -> RecipeList(modifier, favourites ?: listOf(), {recipe -> viewModel.saveToFavourites(recipe)})
                            RecipesTab.ABOUT -> About()

                        }
                    }
                }
                    CircularProgressIndicator(
                        modifier = Modifier
                            .constrainAs(progress) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .visible(isLoading)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RecipeAppBar() {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = Color.Blue,
        modifier = Modifier.height(58.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            text = "Recipe App",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

enum class RecipesTab(
    val title: String,
) {
    RECIPES("Recipes"),
    FAVOURITES("Favourites"),
    ABOUT("About");

    companion object {
        fun getTabFromResource(tabNum: Int): RecipesTab {
            return when (tabNum) {
                0 -> RECIPES
                1 -> FAVOURITES
                2 -> ABOUT
                else -> RECIPES
            }
        }
    }
}

sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")

    object Favourites: NavScreen("Favourites")

    object About : NavScreen("About")
}
