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
    val recipes by viewModel.recipeList.collectAsState(initial = listOf(
        Recipe(null,"Pulled Pork Nachos Recipe","https://edamam-product-images.s3.amazonaws.com/web-img/d65/d65cf3f76548da8bfbe8d76eee75d62a.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEL7%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIQDbYrEiFoUS4lGHhXkq7dl0F6ZdZnkQn3b4OLf96DtPtQIgNrWH%2FrLwJuVZoTyzydeYy8oPkYA3CuYYwywD05UL5XEq2wQIh%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARAAGgwxODcwMTcxNTA5ODYiDCxY0T7SMjyBaYI%2F4CqvBFynTQfFL1rb8psIJWJ1SW7GnHUywuoaxY1EL5rM%2B9ipXjGuQTtjqOIuJjXunpKt0Qh6E9cJUq6n5owZbhiLTybP5Tt5t6KkhrLO1am2lWK%2FxnjtlUhVlttAriWES01wFsc8M0ZRntPzuyH%2FwUaKFwp0uwNFj0tk9K%2FZILLiAPvauy8RS5g5W%2F33p7UUhiTMC8KOMxp8SvhX%2BO3fY9bF%2Bg2ty9cLSd5xFv0SWPPeJhH5IE8cGVc3eI%2By6Or7KhS5PVxvYBiDAGAkUy8JofAWSJWf0nGS27rmzADO8EoY1r9%2Fym9sF231YiCOsK2g71%2FzfSaEbuoezVxEcSMozKsfxw4xR4D%2Bsu4VjTwPtkRdt9OU7en42MEE64YA4sURa5HullaP1Dce8TpbUZsILPodGxQzuoezHBRdqDis75swzCQyeMZwal4uJ0AY0NA9KRD%2BlakAGYTzlXfg3tv7pJpCPoNCfLOwIXOfVqNXOtPEzg%2F0owmLFa9uDSuTd3YAimm4jHIblgbgxNUvU7Ds9mGYkp0CQkPY8TXfiKWoeMghhO3AONyrg9nVes9DotuLdknUMNJBOlF8BB%2B%2FxMf6yxEcFXcuvk0%2F%2FqW7vw9xOJOsZ1W%2Fencm41%2BbhzyqYQO%2BLo5yjMEUHHEBKeVPRS%2Bo3rke8e359BOA3VXLk5Buq3ONqBjOL0D%2BbPnjCN6mkKC8DdLCBmkqUKYcHU0YMljE8Acg28HSF%2FwmeaTLuWryVPa4LW0ws8%2FNkwY6qQE5Ht64FqCdDh7UoMebzAd7C%2B%2BwYnIbLeeC8IldBmzX1qUbklNsKcEdqAtMCWy3ewRn5SWx5aozAEfXBCixr5q3JsqQwBMbrjhZb%2BkQEglFyuZAXRxqZtR6ajoa1I5ko5MByFvHhjBS07gqKU%2FoMBC3K1624p6Joxn7v4mi%2F5kT8OgHHDuB44Odd6NvGn1265Bti7lsB3eSZmlqV4u9mpYkKokfwW88SVCc&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220505T071537Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIASXCYXIIFASRX6QUR%2F20220505%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=8a136ee43ba792d803d8674875e92a6085c074de9a6aa46aceee3691b4755693",3778.9281960000003, 90.0),
        Recipe(null,"Pulled Pork Cornbread Cups","https://edamam-product-images.s3.amazonaws.com/web-img/846/846073908d1a037a4747086f5698bbd4.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEL7%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIQDbYrEiFoUS4lGHhXkq7dl0F6ZdZnkQn3b4OLf96DtPtQIgNrWH%2FrLwJuVZoTyzydeYy8oPkYA3CuYYwywD05UL5XEq2wQIh%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARAAGgwxODcwMTcxNTA5ODYiDCxY0T7SMjyBaYI%2F4CqvBFynTQfFL1rb8psIJWJ1SW7GnHUywuoaxY1EL5rM%2B9ipXjGuQTtjqOIuJjXunpKt0Qh6E9cJUq6n5owZbhiLTybP5Tt5t6KkhrLO1am2lWK%2FxnjtlUhVlttAriWES01wFsc8M0ZRntPzuyH%2FwUaKFwp0uwNFj0tk9K%2FZILLiAPvauy8RS5g5W%2F33p7UUhiTMC8KOMxp8SvhX%2BO3fY9bF%2Bg2ty9cLSd5xFv0SWPPeJhH5IE8cGVc3eI%2By6Or7KhS5PVxvYBiDAGAkUy8JofAWSJWf0nGS27rmzADO8EoY1r9%2Fym9sF231YiCOsK2g71%2FzfSaEbuoezVxEcSMozKsfxw4xR4D%2Bsu4VjTwPtkRdt9OU7en42MEE64YA4sURa5HullaP1Dce8TpbUZsILPodGxQzuoezHBRdqDis75swzCQyeMZwal4uJ0AY0NA9KRD%2BlakAGYTzlXfg3tv7pJpCPoNCfLOwIXOfVqNXOtPEzg%2F0owmLFa9uDSuTd3YAimm4jHIblgbgxNUvU7Ds9mGYkp0CQkPY8TXfiKWoeMghhO3AONyrg9nVes9DotuLdknUMNJBOlF8BB%2B%2FxMf6yxEcFXcuvk0%2F%2FqW7vw9xOJOsZ1W%2Fencm41%2BbhzyqYQO%2BLo5yjMEUHHEBKeVPRS%2Bo3rke8e359BOA3VXLk5Buq3ONqBjOL0D%2BbPnjCN6mkKC8DdLCBmkqUKYcHU0YMljE8Acg28HSF%2FwmeaTLuWryVPa4LW0ws8%2FNkwY6qQE5Ht64FqCdDh7UoMebzAd7C%2B%2BwYnIbLeeC8IldBmzX1qUbklNsKcEdqAtMCWy3ewRn5SWx5aozAEfXBCixr5q3JsqQwBMbrjhZb%2BkQEglFyuZAXRxqZtR6ajoa1I5ko5MByFvHhjBS07gqKU%2FoMBC3K1624p6Joxn7v4mi%2F5kT8OgHHDuB44Odd6NvGn1265Bti7lsB3eSZmlqV4u9mpYkKokfwW88SVCc&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220505T071537Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIASXCYXIIFASRX6QUR%2F20220505%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=49731b6507152588338426ccd3392ed7bb12b9f70ea0d86f6c73a6384a40e9eb",4433.790000000001, 10.0)
    ))
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
    ABOUT("About");

    companion object {
        fun getTabFromResource(tabNum: Int): RecipesTab {
            return when (tabNum) {
                0 -> RECIPES
                1 -> ABOUT
                else -> RECIPES
            }
        }
    }
}

sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")

    object About : NavScreen("About")
}
