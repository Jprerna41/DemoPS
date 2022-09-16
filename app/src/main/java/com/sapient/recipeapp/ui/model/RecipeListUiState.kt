package com.sapient.recipeapp.ui.model

import com.sapient.recipeapp.ui.base.UiStateModel

data class RecipeListUiState(
    val recipeList: List<RecipeUiState>? = listOf(),
    var isLoading: Boolean = false
) : UiStateModel()