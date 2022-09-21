package com.sapient.recipeapp.ui.fragments.recipeList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sapient.recipeapp.domain.model.RecipeDomainModel
import com.sapient.recipeapp.domain.repository.RecipeRepository
import com.sapient.recipeapp.domain.usecase.GetRecipesUseCase
import com.sapient.recipeapp.domain.usecase.InsertFavRecipeUseCase
import com.sapient.recipeapp.domain.usecase.RemoveFavRecipeUseCase
import com.sapient.recipeapp.domain.utils.FakeDataSource
import com.sapient.recipeapp.domain.utils.Resource
import com.sapient.recipeapp.ui.model.RecipeUiState
import com.sapient.recipeapp.ui.model.mapper.IngredientItemMapper
import com.sapient.recipeapp.ui.model.mapper.InstructionItemMapper
import com.sapient.recipeapp.ui.model.mapper.RecipeItemMapper
import com.sapient.recipeapp.ui.model.mapper.StepsItemMapper
import com.sapient.recipeapp.util.MainDispatcherRule
import com.sapient.recipeapp.util.getOrAwaitValue
import com.sapient.recipeapp.util.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalCoroutinesApi::class)
internal class RecipeListViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RecipeListViewModel
    private val repository: RecipeRepository = mock()
    private var dispatcher: CoroutineContext = mock()
    private lateinit var repoItemMapper: RecipeItemMapper
    private lateinit var instructionItemMapper: InstructionItemMapper
    private lateinit var stepsItemMapper: StepsItemMapper
    private lateinit var ingredientItemMapper: IngredientItemMapper

    private lateinit var getRecipesUseCase: GetRecipesUseCase
    private lateinit var insertFavRecipeUseCase: InsertFavRecipeUseCase
    private lateinit var removeFavRecipeUseCase: RemoveFavRecipeUseCase

    private lateinit var recipe: RecipeDomainModel
    private lateinit var recipeList: List<RecipeUiState>

    @Before
    fun setUp() {
        ingredientItemMapper = IngredientItemMapper()
        stepsItemMapper = StepsItemMapper(ingredientItemMapper)
        instructionItemMapper = InstructionItemMapper(stepsItemMapper)
        repoItemMapper = RecipeItemMapper(instructionItemMapper)

        getRecipesUseCase = GetRecipesUseCase(repository)
        insertFavRecipeUseCase = InsertFavRecipeUseCase(repository)
        removeFavRecipeUseCase = RemoveFavRecipeUseCase(repository)
        dispatcher = Dispatchers.IO
        viewModel = RecipeListViewModel(
            getRecipesUseCase,
            insertFavRecipeUseCase,
            removeFavRecipeUseCase,
            dispatcher,
            repoItemMapper
        )

        recipe = FakeDataSource.recipe
        recipeList = listOf(repoItemMapper.mapToUiModel(recipe))

    }

    @Test
    fun testGetRecipe_thenReturn_showLoading() = runTest {
        `when`(repository.requestRecipes()).thenReturn(flow { Resource.Loading(listOf(recipe)) })

        viewModel.getRecipes()

        viewModel.pbLoading.getOrAwaitValue().let {
            this.advanceUntilIdle()
            assertNotNull(it)
            assertEquals(it, true)
        }
    }

    @Test
    fun testSetLiveData_thenReturn_recipeLiveData() = runTest {
        viewModel.setRecipeLiveData(listOf(repoItemMapper.mapToUiModel(recipe)))

        viewModel.recipesData.getOrAwaitValue().let {
            assertNotNull(it)
            assertEquals(it, listOf(repoItemMapper.mapToUiModel(recipe)))
        }
    }

    @Test
    fun testSetRecipeData_emptyList_thenReturnListSize() = runTest {
        viewModel.setRecipeLiveData(emptyList())

        viewModel.recipesData.getOrAwaitValue().let {
            assertNotNull(it)
            assertEquals(it?.size, EMPTY_LIST_SIZE)
        }
    }

    @Test
    fun testGetRecipe_thenReturn_originalRecipeData() = runTest {
        withContext(dispatcher) {
            `when`(repository.requestRecipes()).thenReturn(flow { listOf(recipe) })

            viewModel.setRecipeLiveData(recipeList)

            viewModel.recipesData.getOrAwaitValue().let { recipesList ->
                assertNotNull(recipesList)
                assertEquals(
                    recipesList,
                    recipeList.map { repoItemMapper.mapToUiModel(recipe) })
            }
        }
    }

    @Test
    fun testAddFavRecipe_thenReturn_recipeWithFavTrue() = runTest {
        recipe.isFavourite = true

        `when`(repository.requestRecipes()).thenReturn(flow { listOf(recipe) })

        viewModel.addFavourite(repoItemMapper.mapToUiModel(recipe))

        viewModel.recipesData.observeForever { recipesList ->
            assertNotNull(recipesList)
            assertEquals(recipe.isFavourite, recipesList?.first()?.isFavourite)
        }
    }

    @Test
    fun testAddFavRecipe_asFalse_thenReturnRecipeAsUnFavourite() = runTest {
        recipe.isFavourite = false

        `when`(repository.requestRecipes()).thenReturn(flow { listOf(recipe) })

        viewModel.removeFavourite(repoItemMapper.mapToUiModel(recipe))

        viewModel.recipesData.observeForever { recipesList ->
            assertNotNull(recipesList)
            assertEquals(recipe.isFavourite, recipesList?.first()?.isFavourite)
        }
    }

    @Test
    fun testRequestRecipe_whenNull_thenReturnNullRecipeLiveData() = runTest {
        `when`(repository.requestRecipes()).thenReturn(null)

        viewModel.recipesData.observeForever { recipesList ->
            assertNull(recipesList)
            assertEquals(null, recipesList)
        }
    }

    private companion object {
        const val EMPTY_LIST_SIZE = 0
    }
}