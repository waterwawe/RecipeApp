package com.example.recipeapp.UI.Recipes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeapp.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    recipesRepository: RecipesRepository
    ): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _queryString: MutableLiveData<String> = MutableLiveData<String>().default("")
    val queryString: LiveData<String> get() = _queryString

    private val _selectedTab: MutableState<Int> = mutableStateOf(0)
    val selectedTab: State<Int> get() = _selectedTab

    val recipeList: Flow<List<Recipe>> =
        recipesRepository.loadRecipes(
            onStart = { _isLoading.value = true },
            onCompletion = { _isLoading.value = false },
            queryString.toString()
        )

    fun selectTab(tab: Int) {
        _selectedTab.value = tab
    }

    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
}