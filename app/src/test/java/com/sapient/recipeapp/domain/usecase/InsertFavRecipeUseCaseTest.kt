package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.domain.repository.RecipeRepository
import com.sapient.recipeapp.util.RecipeDomainDataProvider.Companion.getRecipes
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
internal class InsertFavRecipeUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var repository: RecipeRepository

    private lateinit var insertRecipesUseCase: InsertFavRecipeUseCase

    @Before
    fun setUp() {
        insertRecipesUseCase = InsertFavRecipeUseCase(repository = repository)
    }

    @Test
    fun insertRecipe_thenReturn_successEntry() =
        runTest {
            val recipe = getRecipes()
            every { repository.insertFavorite(recipe) } returns Unit

            insertRecipesUseCase(recipe)

            verify(atLeast = 1) { repository.insertFavorite(recipe) }
        }

    @Test
    fun testInsertRecipe_then_throwsException() =
        runTest {
            val recipe = getRecipes()
            every { repository.insertFavorite(recipe) }.throws(Exception())

            var exceptionThrown = false
            try {
                insertRecipesUseCase(recipe)
            } catch (exception: Exception) {
                exceptionThrown = true
            }

            assertTrue(exceptionThrown)
            verify(atLeast = 1) { repository.insertFavorite(recipe) }
        }
}