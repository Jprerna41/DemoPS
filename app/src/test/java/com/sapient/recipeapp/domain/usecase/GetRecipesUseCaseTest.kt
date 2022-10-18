package com.sapient.recipeapp.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sapient.recipeapp.domain.repository.RecipeRepository
import com.sapient.recipeapp.domain.utils.Resource
import com.sapient.recipeapp.util.RecipeDomainDataProvider.Companion.getRecipeDomainList
import com.sapient.recipeapp.util.getChampionDetailsResultDataError
import com.sapient.recipeapp.util.getRecipeResponseFromDB
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetRecipesUseCaseTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var repository: RecipeRepository
    private lateinit var getRecipesUseCase: GetRecipesUseCase

    @Before
    fun setUp() {
        getRecipesUseCase = GetRecipesUseCase(repository)
    }

    @Test
    fun getAllRecipes_thenReturn_successWithRecipesData() =
        runTest {
            coEvery { repository.requestRecipes() } returns getRecipeResponseFromDB()

            val recipes = getRecipesUseCase()

            assertTrue(recipes.first() is Resource.Success)
            assertEquals(recipes.first().data?.size, getRecipeDomainList().size)
        }

    @Test
    fun getAllRecipes_thenReturn_errorWithNoData() =
        runTest {
            coEvery { repository.requestRecipes() } returns getChampionDetailsResultDataError()

            val recipes = getRecipesUseCase()

            assertTrue(recipes.first() is Resource.Error)
            assertEquals(recipes.first().errorMessage, "Data error")
        }
}