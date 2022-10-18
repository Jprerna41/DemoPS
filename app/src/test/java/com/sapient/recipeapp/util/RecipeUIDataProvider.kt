package com.sapient.recipeapp.util

import com.sapient.recipeapp.ui.model.IngredientUiStates
import com.sapient.recipeapp.ui.model.InstructionUiState
import com.sapient.recipeapp.ui.model.RecipeUiState
import com.sapient.recipeapp.ui.model.StepsUiState

class RecipeUIDataProvider {
    companion object {
        private val ingredientUi =
            IngredientUiStates(10511297, "fresh parsley", "fresh parsley", "parsley.jpg")
        private val stepUi = StepsUiState(
            1,
            "Remove the cauliflower's tough stem and reserve for another use. Using a food processor, pulse cauliflower florets until they resemble rice or couscous. You should end up with around four cups of cauliflower rice.",
            listOf(ingredientUi)
        )
        private val instructionEntity = InstructionUiState("instruction1", listOf(stepUi))

        private fun getRecipesUi() = RecipeUiState(
            id = 8723648,
            title = "Test",
            summary = "Test Summary",
            imageUrl = "https://spoonacular.com/recipeImages/716426-312x231.jpg",
            sourceName = "Full Belly Sisters",
            analyzedInstructions = listOf(instructionEntity),
            isFavourite = false
        )

        fun getRecipeUiList(): List<RecipeUiState> {
            return listOf(getRecipesUi(), getRecipesUi())
        }
    }
}