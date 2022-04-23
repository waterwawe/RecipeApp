package com.example.recipeapp.Network

import com.example.recipeapp.DI.NetworkConfig
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.RobolectricTestRunner
import retrofit2.http.QueryMap
import java.util.*
import javax.inject.Inject

/*@RunWith(RobolectricTestRunner::class)
class NetworkTest@Inject constructor(
    private val recipeService: RecipeService
) {

    @Test
    fun apiqueryTest_ShouldBeSuccessful() = runBlockingTest {
        val pulledPork  = "pulled pork"
        val queryParams = HashMap<String,String>();
        queryParams.put("q",pulledPork)
        queryParams.put("type", "public")
        queryParams.put("app_id",NetworkConfig.APP_ID)
        queryParams.put("app_key",NetworkConfig.APP_KEY)


        val apiResponse = recipeService.searchRecipes(queryParams)
        val result = apiResponse.execute()

        assertTrue(result.isSuccessful)
    }*/
}