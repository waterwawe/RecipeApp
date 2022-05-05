package com.example.recipeapp

import com.example.recipeapp.DI.NetworkConfig
import com.example.recipeapp.Network.RecipeService
import kotlinx.coroutines.test.runBlockingTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.robolectric.RobolectricTestRunner
import java.util.HashMap
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ExampleInstrumentedTest {
    @Inject
    lateinit var recipeService: RecipeService

    /*@Test
    fun apiqueryTest_ShouldBeSuccessful() = runBlockingTest {
        val pulledPork  = "pulled pork"
        val queryParams = HashMap<String,String>();
        queryParams.put("q",pulledPork)
        queryParams.put("type", "public")
        queryParams.put("app_id", NetworkConfig.APP_ID)
        queryParams.put("app_key", NetworkConfig.APP_KEY)


        val apiResponse = recipeService.searchRecipes(queryParams)
        val result = apiResponse.execute()

        assertTrue(result.isSuccessful)
    }*/
}