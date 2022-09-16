package com.sapient.recipeapp.presentation.fragments.recipeList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sapient.recipeapp.domain.model.RecipeDomainModel
import com.sapient.recipeapp.domain.repository.RecipeRepository
import com.sapient.recipeapp.domain.usecase.GetRecipesUseCase
import com.sapient.recipeapp.domain.usecase.InsertFavRecipeUseCase
import com.sapient.recipeapp.domain.usecase.RemoveFavRecipeUseCase
import com.sapient.recipeapp.ui.fragments.recipeList.RecipeListViewModel
import com.sapient.recipeapp.ui.model.RecipeUiState
import com.sapient.recipeapp.ui.model.mapper.IngredientItemMapper
import com.sapient.recipeapp.ui.model.mapper.InstructionItemMapper
import com.sapient.recipeapp.ui.model.mapper.RecipeItemMapper
import com.sapient.recipeapp.ui.model.mapper.StepsItemMapper
import com.sapient.recipeapp.util.MainDispatcherRule
import com.sapient.recipeapp.util.RecipeDataProvider.Companion.getTestFavouriteRecipe
import com.sapient.recipeapp.util.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import kotlin.coroutines.CoroutineContext
import kotlin.test.assertNotNull

internal class RecipeListViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RecipeListViewModel
    private val repository: RecipeRepository = mock(RecipeRepository::class.java)
    private val dispatcher: CoroutineContext = mock(CoroutineContext::class.java)
    private lateinit var repoItemMapper: RecipeItemMapper
    private lateinit var instructionItemMapper: InstructionItemMapper
    private lateinit var stepsItemMapper: StepsItemMapper
    private lateinit var ingredientItemMapper: IngredientItemMapper

    private lateinit var getRecipesUseCase: GetRecipesUseCase
    private lateinit var insertFavRecipeUseCase: InsertFavRecipeUseCase
    private lateinit var removeFavRecipeUseCase: RemoveFavRecipeUseCase

    private lateinit var recipe: RecipeDomainModel

    @Before
    fun setUp() {
        ingredientItemMapper = IngredientItemMapper()
        stepsItemMapper = StepsItemMapper(ingredientItemMapper)
        instructionItemMapper = InstructionItemMapper(stepsItemMapper)
        repoItemMapper = RecipeItemMapper(instructionItemMapper)

        getRecipesUseCase = GetRecipesUseCase(repository)
        insertFavRecipeUseCase = InsertFavRecipeUseCase(repository)
        removeFavRecipeUseCase = RemoveFavRecipeUseCase(repository)

        viewModel = RecipeListViewModel(
            getRecipesUseCase,
            insertFavRecipeUseCase,
            removeFavRecipeUseCase,
            dispatcher,
            repoItemMapper
        )

        recipe = getTestFavouriteRecipe()

        val observer = mock<Observer<List<RecipeUiState>?>>()
        viewModel.recipesData.observeForever(observer)
    }

    @Test
    fun `when getRecipe invoked show loading view`() {

        viewModel.getRecipes()

        assertEquals(viewModel.recipesData.value, null)

        assertEquals(viewModel.pbLoading.value, true)
    }

    @Test
    fun `when getRecipe then verify invoke requestRecipe`() {


        `when`(getRecipesUseCase()).thenReturn(flow { listOf(recipe) })

        viewModel.getRecipes()

        verify(repository, times(1)).requestRecipes()
    }

    @Test
    fun `when getRecipe returns the data as emptyList`() {

        `when`(getRecipesUseCase()).thenReturn(flow { emptyList<RecipeUiState>() })

        viewModel.getRecipes()

        viewModel.recipesData.observeForever { recipesList ->
            assertNotNull(recipesList)
            assertEquals(EMPTY_LIST_SIZE, recipesList.size)
        }

    }

    @Test
    fun `when getRecipe returns the data`() = runBlocking {
        withContext(Dispatchers.IO) {

            val recipeList = listOf(recipe)

            `when`(repository.requestRecipes()).thenReturn(flow { listOf(recipe) })

            viewModel.getRecipes()

            viewModel.recipesData.observeForever { recipesList ->
                assertNotNull(recipesList)
                assertEquals(
                    recipesList,
                    recipeList.map { repoItemMapper.mapToUiModel(recipe) })
            }
        }
    }

    @Test
    fun `when insert recipe then verify isFav is true`() {

        recipe.isFavourite = true

        `when`(repository.requestRecipes()).thenReturn(flow { listOf(recipe) })

        viewModel.addFavourite(repoItemMapper.mapToUiModel(recipe))

        viewModel.recipesData.observeForever { recipesList ->
            assertNotNull(recipesList)
            assertEquals(recipe.isFavourite, recipesList.first().isFavourite)
        }
    }

    @Test
    fun `when remove recipe then verify isFav is false`() {

        recipe.isFavourite = false

        `when`(repository.requestRecipes()).thenReturn(flow { listOf(recipe) })

        viewModel.removeFavourite(repoItemMapper.mapToUiModel(recipe))

        viewModel.recipesData.observeForever { recipesList ->
            assertNotNull(recipesList)
            assertEquals(recipe.isFavourite, recipesList.first().isFavourite)
        }
    }

    @Test
    fun `when requestRecipe return null data`() {

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