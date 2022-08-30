package com.sapient.recipeapp.presentation.fragments.recipeList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sapient.recipeapp.data.IRecipeRepository
import com.sapient.recipeapp.data.RecipeRepositoryImpl
import com.sapient.recipeapp.data.Result
import com.sapient.recipeapp.domain.model.Ingredient
import com.sapient.recipeapp.domain.model.Instruction
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.domain.model.Step
import com.sapient.recipeapp.viewModel.CoroutineDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
internal class RecipeListViewModelTest {

    @Mock
    lateinit var observer: Observer<Result<List<RecipeItem>>>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = CoroutineDispatcherRule()

    @Mock
    lateinit var recipeRemoteDataSource: RecipeRepositoryImpl

    private lateinit var viewModel: RecipeListViewModel

    private lateinit var recipeEmptyList: List<RecipeItem>
    private lateinit var recipeList: List<RecipeItem>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = RecipeListViewModel(recipeRemoteDataSource)
        viewModel.recipesLiveData.observeForever(observer)
        mockData()
    }

    @Test
    fun testRecipeDataNotNull() {
        `when`(viewModel.getRecipes()).then { (recipeList) }
        assertNotNull(viewModel.recipesLiveDataPrivate.value)
    }

    @Test
    fun testRecipeDataNull() {
        `when`(viewModel.getRecipes()).then { (recipeEmptyList) }
        assertNull(viewModel.recipesLiveDataPrivate.value!!.data)
    }

    private fun mockData() {
        recipeEmptyList = emptyList()
        val mockList: MutableList<RecipeItem> = mutableListOf()
        val dishType = listOf("side dish", "side dish")
        val ingredient1 = Ingredient(10511297, "fresh parsley", "fresh parsley", "parsley.jpg")
        val ingredient2 = Ingredient(10511298, "fresh parsley", "fresh parsley", "parsley.jpg")
        val ingredients = listOf(ingredient1, ingredient2)
        val step1 = Step(
            1,
            "Remove the cauliflower's tough stem and reserve for another use. Using a food processor, pulse cauliflower florets until they resemble rice or couscous. You should end up with around four cups of cauliflower rice.",
            ingredients
        )
        val step2 = Step(
            2,
            "Remove the cauliflower's tough stem and reserve for another use. Using a food processor, pulse cauliflower florets until they resemble rice or couscous. You should end up with around four cups of cauliflower rice.",
            ingredients
        )
        val steps = listOf(step1, step2)
        val instruction1 = Instruction("instruction1", steps)
        val instruction2 = Instruction("instruction2", steps)

        val analyzedInstructions = listOf(instruction1, instruction2)
        val item = RecipeItem(
            1,
            "Test",
            "Test Summary",
            "https://spoonacular.com/recipeImages/716426-312x231.jpg",
            "jpg",
            "Full Belly Sisters",
            dishType,
            analyzedInstructions,
            steps,
            ingredients
        )


        mockList.add(item)
        mockList.add(item)
        mockList.add(item)

        recipeList = mockList.toList()
    }

}