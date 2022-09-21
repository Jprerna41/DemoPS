package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.domain.utils.FakeDataSource
import com.sapient.recipeapp.domain.model.RecipeDomainModel
import com.sapient.recipeapp.domain.repository.RecipeRepository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doNothing

@OptIn(ExperimentalCoroutinesApi::class)
internal class InsertFavRecipeUseCaseTest {
    private lateinit var insertRecipesUseCase: InsertFavRecipeUseCase
    private val recipeListRepository = mockk<RecipeRepository>()
    private lateinit var recipe: RecipeDomainModel

    @Before
    fun setUp() {
        recipe = FakeDataSource.recipe
        insertRecipesUseCase = InsertFavRecipeUseCase(repository = recipeListRepository)
    }

    @Test
    fun testInsertRecipe_thenReturn_successFullEntry() = runTest {
        every { recipeListRepository.insertFavorite(recipe) } just Runs

        insertRecipesUseCase(recipe)

        verify { recipeListRepository.insertFavorite(recipe) }
    }

    @Test
    fun testInsertRecipe_thenDoNothing() = runTest {
        every { recipeListRepository.insertFavorite(any()) }

        insertRecipesUseCase(recipe)

        verify { recipeListRepository.insertFavorite(FakeDataSource.recipe) wasNot Called }
    }
}