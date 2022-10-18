package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.domain.repository.RecipeRepository
import com.sapient.recipeapp.util.RecipeDomainDataProvider
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
internal class RemoveFavRecipeUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var repository: RecipeRepository

    private lateinit var removeFavRecipeUseCase: RemoveFavRecipeUseCase

    @Before
    fun setUp() {
        removeFavRecipeUseCase = RemoveFavRecipeUseCase(repository = repository)
    }

    @Test
    fun deleteRecipe_thenReturn_successEntry() =
        runTest {
            val recipe = RecipeDomainDataProvider.getRecipes()
            every { repository.deleteFavorite(recipe) } returns Unit

            removeFavRecipeUseCase(recipe)

            verify(atLeast = 1) { repository.deleteFavorite(recipe) }
        }

    @Test
    fun deleteRecipe_then_throwsException() =
        runTest {
            val recipe = RecipeDomainDataProvider.getRecipes()
            every { repository.deleteFavorite(recipe) }.throws(Exception())

            var exceptionThrown = false
            try {
                removeFavRecipeUseCase(recipe)
            } catch (exception: Exception) {
                exceptionThrown = true
            }

            assertTrue(exceptionThrown)
            verify(atLeast = 1) { repository.deleteFavorite(recipe) }
        }
}