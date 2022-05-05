package com.example.recipeapp.Network

import com.example.recipeapp.DI.NetworkConfig
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.*
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class NetworkTest {

    @Inject
    lateinit var recipeService: RecipeService

    @Test
    fun apiqueryTest_ShouldBeSuccessful() = runBlockingTest {
        val pulledPork  = "pulled pork"
        val queryParams = HashMap<String,String>();
        queryParams.put("q",pulledPork)
        queryParams.put("type", "public")
        queryParams.put("app_id",NetworkConfig.APP_ID)
        queryParams.put("app_key",NetworkConfig.APP_KEY)


        val apiResponse = recipeService.searchRecipes(queryParams)
        apiResponse.suspendOnSuccess {
            assertFalse(data.hits.isEmpty())
        }
    }
}