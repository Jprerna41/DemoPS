package com.sapient.recipeapp.domain

import com.sapient.recipeapp.domain.model.Ingredient
import com.sapient.recipeapp.domain.model.Instruction
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.domain.model.Step
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RecipeItemTest {

    private lateinit var recipe: RecipeItem

    @Before
    fun setUp() {
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
        recipe = RecipeItem(
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
    }

    @Test
    fun test_toString() {
        Assert.assertEquals(recipe.title, recipe.toString())
    }

    @Test fun test_default_values() {
        Assert.assertEquals(1, recipe.id)
        Assert.assertEquals(false, recipe.isFav)
    }

}