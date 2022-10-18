package com.sapient.recipeapp.ui.fragments.recipeList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sapient.recipeapp.domain.model.RecipeDomainModel
import com.sapient.recipeapp.domain.repository.RecipeRepository
import com.sapient.recipeapp.domain.usecase.GetRecipesUseCase
import com.sapient.recipeapp.domain.usecase.InsertFavRecipeUseCase
import com.sapient.recipeapp.domain.usecase.RemoveFavRecipeUseCase
import com.sapient.recipeapp.domain.utils.Resource
import com.sapient.recipeapp.ui.model.mapper.IngredientItemMapper
import com.sapient.recipeapp.ui.model.mapper.InstructionItemMapper
import com.sapient.recipeapp.ui.model.mapper.RecipeItemMapper
import com.sapient.recipeapp.ui.model.mapper.StepsItemMapper
import com.sapient.recipeapp.util.*
import com.sapient.recipeapp.util.RecipeDomainDataProvider.Companion.getFavRecipes
import com.sapient.recipeapp.util.RecipeUIDataProvider.Companion.getRecipeUiList
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalCoroutinesApi::class)
internal class RecipeListViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RecipeListViewModel
    private lateinit var repoItemMapper: RecipeItemMapper
    private lateinit var instructionItemMapper: InstructionItemMapper
    private lateinit var stepsItemMapper: StepsItemMapper
    private lateinit var ingredientItemMapper: IngredientItemMapper

    @MockK
    private lateinit var dispatcher: CoroutineContext

    @MockK
    private lateinit var getRecipesUseCase: GetRecipesUseCase

    @MockK
    private lateinit var insertFavRecipeUseCase: InsertFavRecipeUseCase

    @MockK
    private lateinit var removeFavRecipeUseCase: RemoveFavRecipeUseCase

    private lateinit var recipe : RecipeDomainModel

    @Before
    fun setUp() {
        recipe = getFavRecipes()

        ingredientItemMapper = IngredientItemMapper()
        stepsItemMapper = StepsItemMapper(ingredientItemMapper)
        instructionItemMapper = InstructionItemMapper(stepsItemMapper)
        repoItemMapper = RecipeItemMapper(instructionItemMapper)
        dispatcher = Dispatchers.Unconfined
        viewModel = RecipeListViewModel(
            getRecipesUseCase,
            insertFavRecipeUseCase,
            removeFavRecipeUseCase,
            dispatcher,
            repoItemMapper
        )
    }

    @Test
    fun getRecipes_thenReturn_successRecipeListData() =
        runTest {
            every { getRecipesUseCase() } returns getRecipeResponseFromDB()

            viewModel.getRecipes()

            advanceUntilIdle()
            val recipes = viewModel.recipesData.getOrAwaitValue()
            assertTrue(recipes is Resource.Success)
            assertEquals(recipes.data?.size, getRecipeUiList().size)
        }

    @Test
    fun getRecipes_thenReturn_successWithNoRecipeListData() =
        runTest {
            every { getRecipesUseCase() } returns getEmptyResponseFromDB()

            viewModel.getRecipes()

            advanceUntilIdle()
            val recipes = viewModel.recipesData.getOrAwaitValue()
            assertTrue(recipes is Resource.Success)
            assertEquals(recipes.data?.size, 0)
        }

    @Test
    fun getRecipes_thenReturn_errorRecipeListData() =
        runTest {
            every { getRecipesUseCase() } returns getChampionDetailsResultDataError()

            viewModel.getRecipes()

            advanceUntilIdle()
            val recipes = viewModel.recipesData.getOrAwaitValue()
            assertNotNull(recipes is Resource.Error)
            assertEquals(recipes.errorMessage, "Data error")
        }

    @Test
    fun addFavRecipe_thenVerify_insertRecipeInvoked() =
        runTest {
            every { insertFavRecipeUseCase(recipe) } returns Unit

            viewModel.addFavourite(repoItemMapper.mapToUiModel(recipe))

            verify{ insertFavRecipeUseCase(recipe) }
        }

    @Test
    fun removeFavRecipe_thenVerify_removeRecipeInvoked() =
        runTest {
            every { removeFavRecipeUseCase(recipe) } returns Unit

            viewModel.removeFavourite(repoItemMapper.mapToUiModel(recipe))

            verify { removeFavRecipeUseCase(recipe) }
        }
}