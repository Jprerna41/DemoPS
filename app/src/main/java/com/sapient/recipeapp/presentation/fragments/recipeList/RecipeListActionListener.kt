package com.sapient.recipeapp.presentation.fragments.recipeList

import com.sapient.recipeapp.presentation.model.RecipeItem

interface RecipeListActionListener {
    fun onItemSelected(recipe: RecipeItem)
    fun onUpdateFavourite(recipesItem: RecipeItem, position: Int)
}