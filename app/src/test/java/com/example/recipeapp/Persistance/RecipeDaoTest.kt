package com.example.recipeapp.Persistance

import com.example.recipeapp.model.Recipe
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class RecipeDaoTest: LocalDatabase() {
    private lateinit var recipeDao: RecipeDao

    @Before
    fun init(){
        recipeDao = db.recipeDao()
    }

    @Test
    fun insertAndLoadRecipeListTest() = runBlocking {
        val recipe = Recipe(
            id = 1,
            title = "ExampleTitle",
            calories = 123.0,
            timeToMake = 456.0,
            imageSource = "https://edamam-product-images.s3.amazonaws.com/web-img/d65/d65cf3f76548da8bfbe8d76eee75d62a.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEI%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIQDndmyc0qvWjTHHPhtJUblpjhSe1jshP8c1NsCGoES1PgIgL2muTKp8dsE7I7nOAZa9DePt5kCfKTvgPQfG4MBMEH0q%2BgMISBAAGgwxODcwMTcxNTA5ODYiDGCSp%2FWLCoYlyZ99nSrXA9JR%2B8GarKSgosny8Obz%2FV1J%2BOwuFQDoH2JotNzBHDx4NnY%2F2ya%2FAL%2Bw2Tm5p1E2oGL6td%2F7FMAHObPtYvpM%2FUvchZoYlEf%2FAQYL%2FxlgPVD7RaDTGPHHiNkOLDT5VDamw%2F0MC4Xd0Byr3jDVLxDKK0vtmrOGtv2w9im9yD9kkThVSYHFICXbaJ1dKWpTgd46phI4iC3se%2BoGfJoXfENVruealxFLoZJqIFOCHTMF8ssMygLg35DEdfaC0XWWccVneuYGD6wpT%2FKZa%2BxlXj0iEL9YL%2BulQEItJG4TZ0CtD3ex7Kf%2BBcq9ra3ICMSfJiHnV4RDOx3EFp1TcD1V%2FahYECDbzeHKLgGQgt63kqs9yOaWFjgS%2BSpzuox4Y6ECBunS3oJXZsJ8pKxX4Z%2FUXhQTRQSC0QpnYSUMoKb5FIi7hLC06GobmLq%2F7T8eJVArkpf%2BSUnfTyNUAKEe8G3ngjqJS18Siyqth8vG6iDYs4Ksa5FL9XO7byuIXYCzBcuDf1gBqnQ1wUVr9b993gV1YT8%2Fg1bVHHoInd%2FN64Cz75t4gJMj%2BCZl%2F6Pfd7uZXCuv7L%2FwZAoA84eIMHQGCUOcjG%2FH6LgaLyAemOzaKfoVywXqeC2CYf1Zrpz%2FnDCAgYuTBjqlAb1qo6kzh7MQsMcUVWkKhCLdJf590%2B44s6at43uEG1inmQZrtm%2B%2FA0DiditRbPOyanI83ShjmqQWT62FrX%2FEFe%2BEmp6v97m0ceb538cHhu5mKNAcNLSMc4lJvHgTjJ67GRWejyoGXtGQrTCHhFtPDQPdM1CxheIqVgFXNeqzi2XjauPt5s%2F4N%2FEaLhgys0KxX1uZ%2F4QU3TwsqpOR4yI2WdrKuY5YSg%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220422T160539Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIASXCYXIIFKE6DI475%2F20220422%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=8a387ecc940f50552d2270b17eb69c47b4932050c443eea48d917c63468f7144"
        );
        recipeDao.insertRecipe(recipe)

        val recipeInDb = recipeDao.getRecipeList()
        assertThat(recipeInDb[0].id, `is`(1))
        assertThat(recipeInDb[0].title, `is`("ExampleTitle"))
        assertThat(recipeInDb[0].calories, `is`(123.0))
        assertThat(recipeInDb[0].timeToMake, `is`(456.0))
    }
}