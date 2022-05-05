package com.example.recipeapp.UI.Recipes

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.recipeapp.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    recipesRepository: RecipesRepository
    ): ViewModel() {

    @Inject lateinit var recipesRepository: RecipesRepository

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _queryString: MutableLiveData<String> = MutableLiveData<String>().default("pulled pork")
    val queryString: LiveData<String> get() = _queryString

    private val _selectedTab: MutableState<Int> = mutableStateOf(0)
    val selectedTab: State<Int> get() = _selectedTab

    val recipeList: Flow<List<Recipe>> =
        recipesRepository.loadRecipes(
            onStart = { _isLoading.value = true },
            onCompletion = { _isLoading.value = false },
            onError = { Timber.d(it) },
            queryString.value ?: ""
        )

    fun selectTab(tab: Int) {
        _selectedTab.value = tab
        Log.i("Tab changed","asd")
    }

    fun saveToFavourites(recipe: Recipe) {
        recipesRepository.addToFavourites(recipe)
        Log.i("Added recipe",recipe?.title.toString())
    }

    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
}