package com.sapient.recipeapp.ui.model

import android.os.Parcelable
import com.sapient.recipeapp.ui.base.UiStateModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeDetailUiState(
    val id: Int,
    val title: String?,
    val summary: String?,
    val imageUrl: String?,
    val sourceName: String?,
    val analyzedInstructions: List<InstructionUiState>?,
) : UiStateModel(),Parcelable