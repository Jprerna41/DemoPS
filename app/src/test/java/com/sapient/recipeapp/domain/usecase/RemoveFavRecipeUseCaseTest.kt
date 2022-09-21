package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.domain.utils.FakeDataSource
import com.sapient.recipeapp.domain.model.RecipeDomainModel
import com.sapient.recipeapp.domain.repository.RecipeRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

internal class RemoveFavRecipeUseCaseTest {

    private lateinit var deleteUseCase: RemoveFavRecipeUseCase
    private lateinit var recipe: RecipeDomainModel
    private val mockRepository: RecipeRepository = Mockito.mock(RecipeRepository::class.java)

    @Before
    fun setUp() {
        recipe = FakeDataSource.recipe
        deleteUseCase = RemoveFavRecipeUseCase(repository = mockRepository)
    }

    @Test
    fun testDeleteUser_thenVerify_invokeCall() {
        deleteUseCase(recipe)

        verify(mockRepository, times(1)).deleteFavorite(recipe)
    }
}