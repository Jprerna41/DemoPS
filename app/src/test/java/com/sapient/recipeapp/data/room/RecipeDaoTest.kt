package com.sapient.recipeapp.data.room

import android.content.Context
import androidx.room.Room
import com.sapient.recipeapp.data.local.db.RecipeDatabase
import com.sapient.recipeapp.data.local.db.dao.RecipeDao
import com.sapient.recipeapp.data.model.RecipeEntity
import com.sapient.recipeapp.util.RecipeEntityDataProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class RecipeDaoTest {

    private lateinit var userDao: RecipeDao
    private lateinit var db: RecipeDatabase
    private val recipeRepository: Context = Mockito.mock(Context::class.java)
    private lateinit var recipeEntityList: List<RecipeEntity>

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            recipeRepository, RecipeDatabase::class.java
        ).build()
        userDao = db.recipeDao()

        recipeEntityList = RecipeEntityDataProvider.getRecipeEntityList()
    }

    @Test
    fun `test getEntity List`() = runBlocking {

        val recipe: Flow<List<RecipeEntity>> = flow { userDao.getRecipeList() }

        recipe.collect {
            Assert.assertNotNull(recipe)
            Assert.assertEquals(it, recipeEntityList)
        }
    }

    @Test
    fun `test getEntity by id`() = runBlocking {

        val getInsertedRecipe: Flow<RecipeEntity> = flow { userDao.getRecipe(recipeEntityList.first().id) }

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