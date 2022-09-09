package com.sapient.recipeapp

import com.sapient.recipeapp.presentation.model.IngredientItem
import com.sapient.recipeapp.presentation.model.InstructionItem
import com.sapient.recipeapp.presentation.model.StepsItem

class InstructionDataProvider {

    companion object {
        private val ingredientEntity =
            IngredientItem(10511297, "fresh parsley", "fresh parsley", "parsley.jpg")
        private val stepEntity = StepsItem(
            1,
            "Remove the cauliflower's tough stem and reserve for another use. Using a food processor, pulse cauliflower florets until they resemble rice or couscous. You should end up with around four cups of cauliflower rice.",
            listOf(ingredientEntity)
        )
        private val instructionEntity = InstructionItem("instruction1", listOf(stepEntity))

        fun getInstructionsItemList(): List<InstructionItem> {
            return listOf(instructionEntity)
        }
    }
}