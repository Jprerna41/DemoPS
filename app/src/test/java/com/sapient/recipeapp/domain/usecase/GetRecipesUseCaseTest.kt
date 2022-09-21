package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.domain.utils.FakeDataSource
import com.sapient.recipeapp.domain.model.RecipeDomainModel
import com.sapient.recipeapp.domain.repository.RecipeRepository
import com.sapient.recipeapp.domain.utils.Resource
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetRecipesUseCaseTest {

    private lateinit var getRecipesUseCase: GetRecipesUseCase
    private val mockFakeRepo = mockk<RecipeRepository>()
    private lateinit var recipe : RecipeDomainModel

    @Before
    fun setUp() {
        getRecipesUseCase = GetRecipesUseCase(mockFakeRepo)
        recipe = FakeDataSource.recipe
    }

    @Test
    fun getRecipes_thenReturn_recipesListData() = runTest {
        every { mockFakeRepo.requestRecipes() } returns flow { listOf(recipe) }

        val value = mockFakeRepo.requestRecipes()

        assertNotNull(value)
        assertEquals(value, EXPECTED_RECIPE_LIST_SIZE)
    }

    @Test
    fun getRecipes_thenReturn_correctRecipeDataSize() = runTest {
        var actualOutput: Int? = 0
        val value = mockFakeRepo.requestRecipes().first()
        with(value) {
            when (this) {
                is Resource.Success -> {
                    actualOutput = data?.size
                }
                else -> {}
            }
        }

        assertNotNull(actualOutput)
        assertEquals(actualOutput, EXPECTED_RECIPE_LIST_SIZE)
    }

    @Test
    fun getRecipes_thenReturn_correctRecipeId() = runTest {
        every { mockFakeRepo.requestRecipes() } returns flow {
            Resource.Success(
                FakeDataSource.recipe
            )
        }

        var actualId: Int? = 0
        val value = getRecipesUseCase().first()
        with(value) {
            when (this) {
                is Resource.Success -> {
                    actualId = data?.first()?.id
                }
                else -> {}
            }
        }

        assertNotNull(value)
        assertEquals(actualId, EXPECTED_RECIPE_ID)
    }

    private companion object {
        const val EXPECTED_RECIPE_ID = 716426
        const val EXPECTED_RECIPE_LIST_SIZE = 1
    }
}