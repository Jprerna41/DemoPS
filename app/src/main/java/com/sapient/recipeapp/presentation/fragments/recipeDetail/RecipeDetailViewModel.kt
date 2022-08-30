package com.sapient.recipeapp.presentation.fragments.recipeDetail

import androidx.lifecycle.ViewModel
import com.sapient.recipeapp.domain.model.Ingredient
import com.sapient.recipeapp.domain.model.Step
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor() : ViewModel() {

    fun getFormattedSteps(steps: List<Step>): String {
        val stringBuilder = StringBuilder()

        steps.forEachIndexed { index, step ->
            if (index != steps.size - 1) {
                stringBuilder.append("${step.number}. ${step.step}")
                stringBuilder.append("\n")
            } else {
                stringBuilder.append("${step.number}. ${step.step}")
            }
        }

        return stringBuilder.toString()
    }

    fun getFormattedIngredients(ingredients: List<Ingredient>): String {
        val stringBuilder = StringBuilder()

        ingredients.forEachIndexed { index, ingredient ->
            if (index != ingredients.size - 1) {
                stringBuilder.append("- ${ingredient.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }}")
                stringBuilder.append("\n")
            } else {
                stringBuilder.append("- ${ingredient.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }}")
            }
        }

        return stringBuilder.toString()
    }
}