package com.sapient.recipeapp

import com.sapient.recipeapp.domain.model.Ingredient
import com.sapient.recipeapp.domain.model.Instruction
import com.sapient.recipeapp.domain.model.Recipe
import com.sapient.recipeapp.domain.model.Steps

class RecipeDataProvider {

    companion object {
        private val ingredientEntity =
            Ingredient(10511297, "fresh parsley", "fresh parsley", "parsley.jpg")
        private val stepEntity = Steps(
            1,
            "Remove the cauliflower's tough stem and reserve for another use. Using a food processor, pulse cauliflower florets until they resemble rice or couscous. You should end up with around four cups of cauliflower rice.",
            listOf(ingredientEntity)
        )
        private val instructionEntity = Instruction("instruction1", listOf(stepEntity))
        private val dishTypeList = listOf("side dish", "side dish")

        fun getFavouriteRecipe() = Recipe(
            id = 8723648,
            title = "Test",
            summary = "Test Summary",
            imageUrl = "https://spoonacular.com/recipeImages/716426-312x231.jpg",
            imageType = "jpg",
            sourceName = "Full Belly Sisters",
            dishTypes = dishTypeList,
            analyzedInstructions = listOf(instructionEntity),
            isFavourite = true
        )
    }


}