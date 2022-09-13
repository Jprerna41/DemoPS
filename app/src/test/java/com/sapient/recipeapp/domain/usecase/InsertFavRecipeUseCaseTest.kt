package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.domain.model.RecipeDomainModel
import com.sapient.recipeapp.domain.repository.RecipeRepository
import com.sapient.recipeapp.util.RecipeDataProvider
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify


internal class InsertFavRecipeUseCaseTest {

    private lateinit var insertUseCase: InsertFavRecipeUseCase
    private val mockRepository: RecipeRepository = Mockito.mock(RecipeRepository::class.java)

    private lateinit var recipe: RecipeDomainModel

    @Before
    fun setUp() {
        insertUseCase = InsertFavRecipeUseCase(repository = mockRepository)
        recipe = RecipeDataProvider.getTestFavouriteRecipe()
    }

    @Test
    fun `when recipe inserted as fav then invoke repository insert method`() {

        insertUseCase(recipe)

        verify(mockRepository, times(1)).insertFavorite(recipe)
    }
}