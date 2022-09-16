package com.sapient.recipeapp.ui.model

import com.sapient.recipeapp.ui.base.UiStateModel

data class RecipeUiState(
    val id: Int,
    val title: String?,
    val summary: String?,
    val imageUrl: String?,
    val sourceName: String?,
    val analyzedInstructions: List<InstructionUiState>?,
    var isFavourite: Boolean = false,
) : UiStateModel()
