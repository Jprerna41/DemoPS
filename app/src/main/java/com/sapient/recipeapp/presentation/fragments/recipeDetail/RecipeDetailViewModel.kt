package com.sapient.recipeapp.presentation.fragments.recipeDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sapient.recipeapp.presentation.model.IngredientItem
import com.sapient.recipeapp.presentation.model.InstructionItem
import com.sapient.recipeapp.presentation.model.StepsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor() : ViewModel() {

    private val _stepsLiveDataPrivate = MutableLiveData<List<StepsItem>?>()
    val stepsLiveData: LiveData<List<StepsItem>?> = _stepsLiveDataPrivate

    private val _ingredientLiveDataPrivate = MutableLiveData<List<IngredientItem>?>()
    val ingredientLiveData: LiveData<List<IngredientItem>?> = _ingredientLiveDataPrivate

    fun getSeparateIngredientAndStepsList(instruction: List<InstructionItem>?) {
        if (instruction!!.isEmpty()) {
            _stepsLiveDataPrivate.postValue(emptyList())
            _ingredientLiveDataPrivate.postValue(emptyList())
            return
        }
        instruction.forEach { instructionItem ->
            instructionItem.steps?.let { stepList ->
                _stepsLiveDataPrivate.postValue(stepList)
                stepList.forEach { stepItem ->
                    stepItem.ingredients?.let { ingredientList ->
                        _ingredientLiveDataPrivate.postValue(ingredientList)
                    }
                }
            }
        }
    }

    fun getFormattedSteps(steps: List<StepsItem>?): String {
        val stringBuilder = StringBuilder()
        steps?.forEachIndexed { index, step ->
            if (index != steps.size - 1) {
                stringBuilder.append("${step.number}. ${step.step}")
                stringBuilder.append("\n")
            } else {
                stringBuilder.append("${step.number}. ${step.step}")
            }
        }
        return stringBuilder.toString()
    }

    fun getFormattedIngredients(ingredients: List<IngredientItem>?): String {

        val stringBuilder = StringBuilder()
        ingredients?.forEachIndexed { index, ingredient ->
            if (index != ingredients.size - 1) {
                stringBuilder.append(
                    "- ${
                        ingredient.name?.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }
                    }"
                )
                stringBuilder.append("\n")
            } else {
                stringBuilder.append(
                    "- ${
                        ingredient.name?.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }
                    }"
                )
            }
        }
        return stringBuilder.toString()
    }

}