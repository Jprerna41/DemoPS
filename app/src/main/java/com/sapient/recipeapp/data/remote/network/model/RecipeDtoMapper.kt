package com.sapient.recipeapp.data.remote.network.model

import com.sapient.recipeapp.data.remote.response.RecipeResponse
import com.sapient.recipeapp.domain.mapper.DomainMapper
import com.sapient.recipeapp.domain.model.Ingredient
import com.sapient.recipeapp.domain.model.Instruction
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.domain.model.Step

class RecipeDtoMapper : DomainMapper<RecipeResponse, RecipeItem> {

    override fun mapToDomainModel(
        model: RecipeResponse
    ): RecipeItem {
        val steps = ArrayList<Step>()
        val ingredients = ArrayList<Ingredient>()

        model.analyzedInstructions?.forEach { instructionResponse ->
            instructionResponse.steps?.forEach { stepResponse ->
                steps.add(
                    Step(
                        number = stepResponse.number ?: 0,
                        step = stepResponse.step ?: "",
                        ingredients = stepResponse.ingredients?.map { ingredientResponse ->
                            Ingredient(
                                id = ingredientResponse.id ?: 0,
                                name = ingredientResponse.name ?: "",
                                localizedName = ingredientResponse.localizedName ?: "",
                                image = ingredientResponse.image ?: ""
                            )
                        } ?: emptyList()
                    )
                )
            }
        }

        model.analyzedInstructions?.forEach { instructionResponse ->
            instructionResponse.steps?.forEach { stepResponse ->
                stepResponse.ingredients?.forEach { ingredientResponse ->
                    if (!ingredients.any { it.id == ingredientResponse.id }) {
                        ingredients.add(
                            Ingredient(
                                id = ingredientResponse.id ?: 0,
                                name = ingredientResponse.name ?: "",
                                localizedName = ingredientResponse.localizedName ?: "",
                                image = ingredientResponse.image ?: ""
                            )
                        )
                    }
                }
            }
        }

        return RecipeItem(
            id = model.id,
            title = model.title ?: "",
            summary = model.summary ?: "",
            imageUrl = model.imageUrl ?: "",
            imageType = model.imageType ?: "",
            sourceName = model.sourceName ?: "",
            dishTypes = model.dishTypes ?: emptyList(),
            analyzedInstructions = model.analyzedInstructions?.map { instructionResponse ->
                Instruction(
                    name = instructionResponse.name ?: "",
                    steps = instructionResponse.steps?.map { stepResponse ->
                        Step(
                            number = stepResponse.number ?: 0,
                            step = stepResponse.step ?: "",
                            ingredients = stepResponse.ingredients?.map { ingredientResponse ->
                                Ingredient(
                                    id = ingredientResponse.id ?: 0,
                                    name = ingredientResponse.name ?: "",
                                    localizedName = ingredientResponse.localizedName ?: "",
                                    image = ingredientResponse.image ?: ""
                                )
                            } ?: emptyList()
                        )
                    } ?: emptyList()
                )
            } ?: emptyList(),
            steps = steps,
            ingredients = ingredients,
        )
    }


    fun toDomainList(initial: List<RecipeResponse>): List<RecipeItem> {
        return initial.map { mapToDomainModel(it) }
    }

}
