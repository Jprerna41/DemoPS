package db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.sapient.recipeapp.data.local.db.RecipeDatabase
import com.sapient.recipeapp.data.local.db.dao.RecipeDao
import db.RecipeEntityDataProvider.Companion.getRecipeEntity
import db.RecipeEntityDataProvider.Companion.getRecipeEntityList
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*

class RecipeDaoTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var recipesDao: RecipeDao
    private lateinit var recipesDatabase: RecipeDatabase

    @Before
    fun setup() {
        recipesDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RecipeDatabase::class.java
        ).allowMainThreadQueries().build()

        recipesDao = recipesDatabase.recipeDao()
    }

    @After
    fun tearDown() {
        recipesDatabase.close()
    }

    @Test
    fun insertRecipe_thenItemInsertedIntoDB_success() {
        runBlocking {
            val item = getRecipeEntity()
            recipesDao.insertRecipe(item)
            val returnedList = recipesDao.getRecipe(item.id)
            Assert.assertEquals(item, returnedList.first())
        }
    }

    @Test
    fun getAllRecipes_thenAllRecipesRetrieved_success(){
        runBlocking {
            val itemList = getRecipeEntityList()
            recipesDao.insertAllRecipes(itemList)
            val returnedList = recipesDao.getRecipeList()
            Assert.assertEquals(itemList.size, returnedList.first().size)
        }
    }

    @Test
    fun deleteRecipes_thenRemoveFromDB_success(){
        runBlocking {
            val itemList = getRecipeEntityList()
            recipesDao.insertAllRecipes(itemList)
            recipesDao.deleteRecipe(itemList[0])
            val returnedList = recipesDao.getRecipeList()
            Assert.assertEquals(1, returnedList.first().size)
        }
    }




}