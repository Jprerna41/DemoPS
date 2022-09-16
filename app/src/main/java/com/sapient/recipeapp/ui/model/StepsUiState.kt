package com.sapient.recipeapp.ui.model

import android.os.Parcelable
import com.sapient.recipeapp.ui.base.UiStateModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class StepsUiState(
    val number: Int,
    val step: String?,
    val ingredients: List<IngredientUiStates>?
) : UiStateModel(),Parcelable