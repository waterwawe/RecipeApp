package com.example.recipeapp.DI

import com.example.recipeapp.Network.RecipeService
import com.example.recipeapp.Persistance.RecipeDao
import com.example.recipeapp.UI.Recipes.RecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideRecipesRepository(
        recipeService: RecipeService,
        recipeDao: RecipeDao
    ): RecipesRepository {
        return RecipesRepository(recipeService, recipeDao)
    }
}