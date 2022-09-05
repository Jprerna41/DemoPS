package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.createRecipeItem
import com.sapient.recipeapp.data.Result
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.domain.repository.IRecipeRepository
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

internal class RecipeUseCaseTest {

    private lateinit var recipeUseCase: RecipeUseCase
    private val recipeRepository: IRecipeRepository = Mockito.mock(IRecipeRepository::class.java)
    private val recipeList: List<RecipeItem> = listOf(createRecipeItem())
    private val recipeItem = createRecipeItem()

    @Before
    fun setUp() {
        recipeUseCase = RecipeUseCase(recipeRepository)
    }

    @Test
    fun testGetRecipes() {
        val expectedRecipeItemSize = 1
        `when`(recipeUseCase.getRecipes()).thenReturn(flow { Result.Success(recipeList) })
        Assert.assertNotNull(recipeList)
        Assert.assertTrue(recipeList.size == expectedRecipeItemSize)
    }

    @Test
    fun testIsFavorite() {
        `when`(recipeUseCase.isFavorite(createRecipeItem())).thenReturn(flow {
            Result.Success(
                recipeItem.isFavourite
            )
        })
        Assert.assertNotNull(recipeItem.isFavourite)
        Assert.assertTrue(recipeItem.isFavourite)
    }
}