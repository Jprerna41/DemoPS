package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.domain.utils.Resource
import com.sapient.recipeapp.domain.repository.RecipeRepository
import com.sapient.recipeapp.util.RecipeEntityDataProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify


internal class GetRecipesUseCaseTest {

    private lateinit var mockRepository: RecipeRepository
    private lateinit var useCase: GetRecipesUseCase

    @Before
    fun setUp() {
        mockRepository = mock {
            on { requestRecipes() } doReturn flow {
                Resource.Success(listOf(RecipeEntityDataProvider.getRecipeEntityList()))
            }
        }
        useCase = GetRecipesUseCase(repository = mockRepository)
    }


    @Test
    fun `test getRecipes method call`() {
        useCase()
        verify(mockRepository, times(1)).requestRecipes()
    }

    @Test
    fun `test expected recipe ids`() = runBlocking {

        useCase().collect { result ->
            result.data?.map {
                Assert.assertEquals(EXPECTED_RECIPE_ID, it.id)
            }
        }
        verify(mockRepository, times(1)).requestRecipes()
    }

    @Test
    fun `test getRecipe return empty list`() = runBlocking {

        `when`(useCase()).thenReturn(flow { RecipeEntityDataProvider.getRecipeEntityList() })

        useCase().collect { result ->
            Assert.assertEquals(EXPECTED_RECIPE_LIST_SIZE, result.data?.size)
        }
    }

    private companion object {
        const val EXPECTED_RECIPE_ID = 8723648
        const val EXPECTED_RECIPE_LIST_SIZE = 0
    }
}