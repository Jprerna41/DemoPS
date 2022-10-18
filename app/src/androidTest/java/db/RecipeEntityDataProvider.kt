package db

import com.sapient.recipeapp.data.model.IngredientEntity
import com.sapient.recipeapp.data.model.InstructionEntity
import com.sapient.recipeapp.data.model.RecipeEntity
import com.sapient.recipeapp.data.model.StepEntity

class RecipeEntityDataProvider {

    companion object {
        private val ingredientEntity = IngredientEntity(10511297, "fresh parsley", "fresh parsley", "parsley.jpg")
        private val stepEntity = StepEntity(
            1,
            "Remove the cauliflower's tough stem and reserve for another use. Using a food processor, pulse cauliflower florets until they resemble rice or couscous. You should end up with around four cups of cauliflower rice.",
            listOf(ingredientEntity)
        )
        private val instructionEntity = InstructionEntity("instruction1", listOf(stepEntity))
        private val dishTypeList = listOf("side dish", "side dish")

        fun getRecipeEntity() = RecipeEntity(
            id = 8723648,
            title = "Test",
            summary = "Test Summary",
            image = "https://spoonacular.com/recipeImages/716426-312x231.jpg",
            imageType = "jpg",
            sourceName = "Full Belly Sisters",
            dishTypes = dishTypeList,
            analyzedInstructions = listOf(instructionEntity),
            isFavourite = false
        )

        private fun getRecipeEntity1() = RecipeEntity(
            id = 8723649,
            title = "Test",
            summary = "Test Summary",
            image = "https://spoonacular.com/recipeImages/716426-312x231.jpg",
            imageType = "jpg",
            sourceName = "Full Belly Sisters",
            dishTypes = dishTypeList,
            analyzedInstructions = listOf(instructionEntity),
            isFavourite = false
        )

        fun getRecipeEntityList(): List<RecipeEntity> {
            return listOf(getRecipeEntity(), getRecipeEntity1())
        }
    }
}