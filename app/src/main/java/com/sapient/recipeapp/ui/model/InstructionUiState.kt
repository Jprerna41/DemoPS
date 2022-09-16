package com.sapient.recipeapp.ui.model

import android.os.Parcelable
import com.sapient.recipeapp.ui.base.UiStateModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class InstructionUiState(
    val name: String?,
    val steps: List<StepsUiState>?
) : UiStateModel(),Parcelable