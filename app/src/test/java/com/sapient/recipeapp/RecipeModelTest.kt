package com.sapient.recipeapp

import com.sapient.recipeapp.data.local.entity.IngredientEntity
import com.sapient.recipeapp.data.local.entity.InstructionEntity
import com.sapient.recipeapp.data.local.entity.RecipeEntity
import com.sapient.recipeapp.data.local.entity.StepEntity
import com.sapient.recipeapp.data.remote.response.IngredientResponse
import com.sapient.recipeapp.data.remote.response.InstructionResponse
import com.sapient.recipeapp.data.remote.response.RecipeResponse
import com.sapient.recipeapp.data.remote.response.StepResponse
import com.sapient.recipeapp.domain.model.Ingredient
import com.sapient.recipeapp.domain.model.Instruction
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.domain.model.Step

// Domain Data
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
val analyzedInstructions = listOf(instruction1,instruction2)
fun createRecipeItem(): RecipeItem = RecipeItem(
    1,
    "Test",
    "Test Summary",
    "https://spoonacular.com/recipeImages/716426-312x231.jpg",
    "jpg",
    "Full Belly Sisters",
    dishType,
    analyzedInstructions,
    steps,
    ingredients,
    true
)

// Response Model Data
val ingredientResponse = IngredientResponse(10511297, "fresh parsley", "fresh parsley", "parsley.jpg")
val ingredientResponses = listOf(ingredientResponse)
val stepResponse1 = StepResponse(
    1,
    "Remove the cauliflower's tough stem and reserve for another use. Using a food processor, pulse cauliflower florets until they resemble rice or couscous. You should end up with around four cups of cauliflower rice.",
    ingredientResponses
)
val stepResponses = listOf(stepResponse1)
val instructionResponse2= InstructionResponse("instruction2", stepResponses)
val analyzedInstructionsResponse = listOf(instructionResponse2)
fun createRecipeResponseItem(): RecipeResponse = RecipeResponse(
    1,
    "Test",
    "Test Summary",
    "https://spoonacular.com/recipeImages/716426-312x231.jpg",
    "jpg",
    "Full Belly Sisters",
    dishType,
    analyzedInstructionsResponse
)

// Entity Data
val ingredientEntity1 = IngredientEntity(10511297, "fresh parsley", "fresh parsley", "parsley.jpg")
val ingredientEntity2 = IngredientEntity(10511298, "fresh parsley", "fresh parsley", "parsley.jpg")
val ingredientEntities = listOf(ingredientEntity1, ingredientEntity2)
val stepEntity1 = StepEntity(
    1,
    "Remove the cauliflower's tough stem and reserve for another use. Using a food processor, pulse cauliflower florets until they resemble rice or couscous. You should end up with around four cups of cauliflower rice.",
    ingredientEntities
)
val stepEntity2 = StepEntity(
    2,
    "Remove the cauliflower's tough stem and reserve for another use. Using a food processor, pulse cauliflower florets until they resemble rice or couscous. You should end up with around four cups of cauliflower rice.",
    ingredientEntities
)
val stepsEntity = listOf(stepEntity1, stepEntity2)
val instructionEntity1 = InstructionEntity("instruction1", stepsEntity)
val instructionEntity2 = InstructionEntity("instruction2", stepsEntity)
val analyzedInstructionsEntity = listOf(instructionEntity1, instructionEntity2)
fun createRecipeEntity(): RecipeEntity = RecipeEntity( 1,
    "Test",
    "Test Summary",
    "https://spoonacular.com/recipeImages/716426-312x231.jpg",
    "jpg",
    "Full Belly Sisters",
    dishType,
    analyzedInstructionsEntity)

