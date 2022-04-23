package com.example.recipeapp.DI

import android.app.Application
import androidx.room.Room
import com.example.recipeapp.Persistance.AppDatabase
import com.example.recipeapp.Persistance.RecipeDao
import com.example.recipeapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                application.getString(R.string.dbname)
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePosterDao(appDatabase: AppDatabase): RecipeDao {
        return appDatabase.recipeDao()
    }
}