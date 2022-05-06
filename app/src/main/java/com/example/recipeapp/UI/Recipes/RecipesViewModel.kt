package com.example.recipeapp.UI.Recipes

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.recipeapp.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class RecipesViewModel @Inject constructor(
    recipesRepository: RecipesRepository
    ): ViewModel() {

    @Inject lateinit var recipesRepository: RecipesRepository

    private val _recipeList: MutableLiveData<List<Recipe>> = MutableLiveData(listOf())
    val recipeList: LiveData<List<Recipe>> get() = _recipeList

    private val _favourites: MutableLiveData<List<Recipe>> = MutableLiveData(listOf())
    val favourites: LiveData<List<Recipe>> get() = _favourites

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _queryString: MutableLiveData<String> = MutableLiveData<String>().default("pulled pork")
    val queryString: LiveData<String> get() = _queryString

    private val _selectedTab: MutableState<Int> = mutableStateOf(0)
    val selectedTab: State<Int> get() = _selectedTab


    fun selectTab(tab: Int) {
        _selectedTab.value = tab
        if(tab == 0){
            getRecipes(_queryString.value?: "");
        }
        if(tab == 1 ){
            getFavourites();
        }
    }

    fun getRecipes(queryString:String){
        var list = listOf<Recipe>()
        _queryString.value = queryString
        viewModelScope.launch {
            list = recipesRepository.loadRecipes(queryString)
            _recipeList.value = list
        }
    }

    fun saveToFavourites(recipe: Recipe) {
        viewModelScope.launch {
            recipesRepository.addToFavourites(recipe)
        }
    }

    fun deleteFromFavourites(recipe: Recipe) {
        viewModelScope.launch {
            recipesRepository.deleteFromFavourites(recipe)
            getFavourites()
        }
    }

    fun getFavourites() {
        var list = listOf<Recipe>()
        viewModelScope.launch {
            list = recipesRepository.getFavourites()
            _favourites.value = list
        }
    }

    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
}