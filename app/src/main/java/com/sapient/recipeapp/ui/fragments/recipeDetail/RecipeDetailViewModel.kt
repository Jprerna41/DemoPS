package com.sapient.recipeapp.ui.fragments.recipeDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sapient.recipeapp.ui.model.IngredientUiStates
import com.sapient.recipeapp.ui.model.InstructionUiState
import com.sapient.recipeapp.ui.model.StepsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor() : ViewModel() {

    private val _stepsLiveDataPrivate = MutableLiveData<List<StepsUiState>>()
    val stepsLiveData: LiveData<List<StepsUiState>> = _stepsLiveDataPrivate

    private val _ingredientLiveDataPrivate = MutableLiveData<List<IngredientUiStates>>()
    val ingredientLiveData: LiveData<List<IngredientUiStates>> = _ingredientLiveDataPrivate

    fun getSeparateIngredientAndStepsList(instruction: List<InstructionUiState>) {
        if (instruction.isEmpty()) {
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

    fun getFormattedSteps(steps: List<StepsUiState>?): String {
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

    fun getFormattedIngredients(ingredients: List<IngredientUiStates>?): String {

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