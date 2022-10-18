package com.sapient.recipeapp.util

import com.sapient.recipeapp.domain.model.IngredientDomainModel
import com.sapient.recipeapp.domain.model.InstructionDomainModel
import com.sapient.recipeapp.domain.model.RecipeDomainModel
import com.sapient.recipeapp.domain.model.StepsDomainModel

class RecipeDomainDataProvider {
    companion object {
        private val ingredient =
            IngredientDomainModel(10511297, "fresh parsley", "fresh parsley", "parsley.jpg")
        private val step = StepsDomainModel(
            1,
            "Remove the cauliflower's tough stem and reserve for another use. Using a food processor, pulse cauliflower florets until they resemble rice or couscous. You should end up with around four cups of cauliflower rice.",
            listOf(ingredient)
        )
        private val instructionEntity = InstructionDomainModel("instruction1", listOf(step))

        fun getRecipes() = RecipeDomainModel(
            id = 8723648,
            title = "Test",
            summary = "Test Summary",
            imageUrl = "https://spoonacular.com/recipeImages/716426-312x231.jpg",
            sourceName = "Full Belly Sisters",
            analyzedInstructions = listOf(instructionEntity),
            isFavourite = false
        )

        fun getFavRecipes() = RecipeDomainModel(
            id = 8723649,
            title = "Test Fav",
            summary = "Test Fav Summary",
            imageUrl = "https://spoonacular.com/recipeImages/716426-312x231.jpg",
            sourceName = "Full Belly Sisters",
            analyzedInstructions = listOf(instructionEntity),
            isFavourite = true
        )

        fun getRecipeDomainList(): List<RecipeDomainModel> {
            return listOf(getRecipes(), getFavRecipes())
        }
    }
}