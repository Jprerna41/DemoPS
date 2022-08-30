package com.sapient.recipeapp.data

import com.sapient.recipeapp.data.remote.network.RecipeServices
import com.sapient.recipeapp.data.remote.network.model.RecipeDtoMapper
import com.sapient.recipeapp.domain.model.Ingredient
import com.sapient.recipeapp.domain.model.Instruction
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.domain.model.Step
import com.sapient.recipeapp.viewModel.CoroutineDispatcherRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class RecipeRepositoryImplTest {


    private val services = mockk<RecipeServices>(relaxed = true)
    private val mapper = mockk<RecipeDtoMapper>(relaxed = true)
    private lateinit var repository: RecipeRepositoryImpl

    private lateinit var recipeEmptyList: List<RecipeItem>
    private lateinit var recipeList: List<RecipeItem>

    @get:Rule
    val dispatcherRule = CoroutineDispatcherRule()


    @Before
    fun setUp() {
        repository = RecipeRepositoryImpl(services, mapper)
        mockData()
    }

    @Test
    fun requestRecipes() {
        runBlocking {
            Assert.assertNotNull(repository.requestRecipes())
        }
    }

    private fun mockData() : Flow<List<RecipeItem>> {
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

        return listOf(recipeList).asFlow()
    }
}