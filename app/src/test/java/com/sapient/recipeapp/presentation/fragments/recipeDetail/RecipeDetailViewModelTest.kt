package com.sapient.recipeapp.presentation.fragments.recipeDetail

import com.sapient.recipeapp.ingredients
import com.sapient.recipeapp.steps
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class RecipeDetailViewModelTest {

    private lateinit var viewModel: RecipeDetailViewModel

    @Before
    fun setUp() {
        viewModel = RecipeDetailViewModel()
    }

    @Test
    fun testGetFormattedSteps() {
        val expectedFormattedString =
            "1. Remove the cauliflower's tough stem and reserve for another use. Using a food processor, pulse cauliflower florets until they resemble rice or couscous. You should end up with around four cups of cauliflower rice.\n" +
                    "2. Remove the cauliflower's tough stem and reserve for another use. Using a food processor, pulse cauliflower florets until they resemble rice or couscous. You should end up with around four cups of cauliflower rice."
        val actualFormattedString = viewModel.getFormattedSteps(steps)
        assertNotNull(viewModel.getFormattedSteps(steps))
        assertEquals(expectedFormattedString, actualFormattedString)
    }

    @Test
    fun testGetFormattedIngredients() {
        val expectedFormattedString = "- Fresh parsley\n" +
                "- Fresh parsley"
        val actualFormattedString = viewModel.getFormattedIngredients(ingredients)
        assertNotNull(viewModel.getFormattedIngredients(ingredients))
        assertEquals(expectedFormattedString, actualFormattedString)
    }
}