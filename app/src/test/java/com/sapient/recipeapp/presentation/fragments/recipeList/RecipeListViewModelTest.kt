package com.sapient.recipeapp.presentation.fragments.recipeList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sapient.recipeapp.createRecipeItem
import com.sapient.recipeapp.data.Result
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.domain.usecase.RecipeUseCase
import com.sapient.recipeapp.getValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
internal class RecipeListViewModelTest {

    private val hiltRule = HiltAndroidRule(this)
    private val useCase: RecipeUseCase = Mockito.mock(RecipeUseCase::class.java)
    private lateinit var viewModel: RecipeListViewModel
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var recipeEmptyList: List<RecipeItem>
    private lateinit var recipeList: List<RecipeItem>

    @Inject
    lateinit var repository: CoroutineContext

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)!!

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = RecipeListViewModel(useCase, repository)
        viewModel.recipesLiveData.observeForever { }
        recipeEmptyList = emptyList()
        recipeList = listOf(createRecipeItem())
    }

    @Test
    fun testGetRecipesLiveDataNotNull() {
        Mockito.`when`(useCase.getRecipes()).thenReturn(flow {
            Result.Success(recipeList)
        })
        viewModel.getRecipes()
        Assert.assertNotNull(getValue(viewModel.recipesLiveData))
    }

    @Test
    fun testGetRecipesLiveDataNull() {
        Mockito.`when`(useCase.getRecipes()).thenReturn(flow {
            Result.Success(recipeEmptyList)
        })
        Assert.assertNull(getValue(viewModel.recipesLiveData))
    }

}
