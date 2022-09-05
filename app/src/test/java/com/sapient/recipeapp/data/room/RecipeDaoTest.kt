package com.sapient.recipeapp.data.room

import android.content.Context
import androidx.room.Room
import com.sapient.recipeapp.createRecipeEntity
import com.sapient.recipeapp.data.local.entity.RecipeEntity
import com.sapient.recipeapp.data.local.room.RecipeDao
import com.sapient.recipeapp.data.local.room.RecipeDatabase
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


    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            recipeRepository, RecipeDatabase::class.java
        ).build()
        userDao = db.recipeDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun testGetEntityList() {
        val recipeEntityList = listOf(createRecipeEntity())
        val recipe: Flow<List<RecipeEntity>> = flow { userDao.getRecipeList() }
        runBlocking {
            recipe.collect {
                Assert.assertNotNull(recipe)
                Assert.assertEquals(it, recipeEntityList)
            }
        }
    }

    @Test
    fun testGetEntityById() {
        val recipeEntity = createRecipeEntity()
        val getInsertedRecipe: Flow<RecipeEntity> = flow { userDao.getRecipe(recipeEntity.id) }
        runBlocking {
            getInsertedRecipe.collect {
                Assert.assertNotNull(it)
                Assert.assertEquals(recipeEntity.id, it!!.id)
            }
        }
    }

}