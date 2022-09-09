package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.RecipeEntityDataProvider
import com.sapient.recipeapp.data.Resource
import com.sapient.recipeapp.domain.repository.RecipeRepository
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
        useCase.getRecipes()
        verify(mockRepository, times(1)).requestRecipes()
    }

    @Test
    fun `test expected recipe ids`() {
        val expectedRecipeId = 8723648
        val res = useCase.getRecipes()
        runBlocking {
            res.collect { result ->
                result.data?.map {
                    Assert.assertEquals(expectedRecipeId, it.id)
                }
            }
        }
        verify(mockRepository, times(1)).requestRecipes()
    }

    @Test
    fun `test getRecipe return empty list`() {
        val expectedListSize = 0
        `when`(useCase.getRecipes()).thenReturn(flow { RecipeEntityDataProvider.getRecipeEntityList() })
        val res = useCase.getRecipes()
        runBlocking {
            res.collect { result ->
                Assert.assertEquals(expectedListSize, result.data!!.size)
            }
        }
    }
}