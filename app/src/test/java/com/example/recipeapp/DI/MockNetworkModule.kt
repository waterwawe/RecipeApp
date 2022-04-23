package com.example.recipeapp.DI

import com.example.recipeapp.DI.interceptors.MockInterceptor
import com.example.recipeapp.Network.RecipeService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MockNetworkModule {
    private val networkModule = NetworkModule

    @Provides
    @Singleton
    fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient {
        builder.interceptors().add(builder.interceptors().size, MockInterceptor())

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return networkModule.provideRetrofit(client)
    }

    @Provides
    @Singleton
    fun provideRecipeService(retrofit: Retrofit): RecipeService {
        return networkModule.provideRecipeService(retrofit)
    }
}