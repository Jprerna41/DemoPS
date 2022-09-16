package com.sapient.recipeapp.ui.fragments.recipeList

import com.sapient.recipeapp.ui.model.RecipeUiState

interface RecipeListActionListener {
    fun onItemSelected(recipe: RecipeUiState)
    fun onUpdateFavourite(recipesItem: RecipeUiState, position: Int)
}