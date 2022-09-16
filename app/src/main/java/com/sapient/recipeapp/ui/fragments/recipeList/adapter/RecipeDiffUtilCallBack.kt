package com.sapient.recipeapp.ui.fragments.recipeList.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sapient.recipeapp.ui.model.RecipeUiState

class RecipeDiffUtilCallBack : DiffUtil.ItemCallback<RecipeUiState?>() {
    override fun areItemsTheSame(oldItem: RecipeUiState, newItem: RecipeUiState): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecipeUiState, newItem: RecipeUiState): Boolean {
        return oldItem == newItem
    }
}