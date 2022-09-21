@file:OptIn(ExperimentalCoroutinesApi::class)

package com.sapient.recipeapp.data.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sapient.recipeapp.data.local.db.RecipeDatabase
import com.sapient.recipeapp.data.local.db.dao.RecipeDao
import com.sapient.recipeapp.data.model.RecipeEntity
import com.sapient.recipeapp.util.RecipeEntityDataProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class RecipeDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var userDao: RecipeDao
    private lateinit var db: RecipeDatabase
    private lateinit var recipeEntityList: List<RecipeEntity>

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RecipeDatabase::class.java
        )
            .build()
        userDao = db.recipeDao()

        recipeEntityList = RecipeEntityDataProvider.getRecipeEntityList()
    }

    @Test
    fun testGetRecipeList_thenReturn_CorrectDbData() = runTest {
        val recipe: Flow<List<RecipeEntity>> = flow { userDao.getRecipeList() }

        recipe.collect {
            Assert.assertNotNull(recipe)
            Assert.assertEquals(it, recipeEntityList)
        }
    }

    @Test
    fun testInsertRecipe_thenReturn_correctRecipeById() = runBlocking {
        val getInsertedRecipe: Flow<RecipeEntity> =
            flow { userDao.getRecipe(recipeEntityList.first().id) }

        getInsertedRecipe.collect {
            Assert.assertNotNull(it)
            Assert.assertEquals(recipeEntityList.first().id, it.id)
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}
