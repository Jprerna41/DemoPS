package com.sapient.recipeapp.presentation.fragments.recipeList.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sapient.recipeapp.presentation.model.RecipeItem

class RecipeDiffUtilCallBack : DiffUtil.ItemCallback<RecipeItem?>() {
    override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
        return oldItem == newItem
    }
}