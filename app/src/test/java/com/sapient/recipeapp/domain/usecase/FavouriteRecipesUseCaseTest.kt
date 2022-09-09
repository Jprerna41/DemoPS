package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.RecipeDataProvider
import com.sapient.recipeapp.domain.repository.RecipeRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify


internal class FavouriteRecipesUseCaseTest {

    private lateinit var useCase: FavouriteRecipesUseCase
    private val mockRepository: RecipeRepository = Mockito.mock(RecipeRepository::class.java)

    @Before
    fun setUp() {
        useCase = FavouriteRecipesUseCase(repository = mockRepository)
    }

    @Test
    fun `test insert Favorite call`() {
        useCase.insertFavorite(RecipeDataProvider.getFavouriteRecipe())
        verify(mockRepository, times(1)).insertFavorite(RecipeDataProvider.getFavouriteRecipe())
    }

    @Test
    fun `test delete favourite`() {
        useCase.deleteFavorite(RecipeDataProvider.getFavouriteRecipe())
        verify(mockRepository, times(1)).deleteFavorite(RecipeDataProvider.getFavouriteRecipe())
    }
}