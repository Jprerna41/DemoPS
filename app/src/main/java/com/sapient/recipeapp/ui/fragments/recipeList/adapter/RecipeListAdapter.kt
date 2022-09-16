package com.sapient.recipeapp.ui.fragments.recipeList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sapient.recipeapp.databinding.ItemRecipeBinding
import com.sapient.recipeapp.ui.fragments.recipeList.RecipeListActionListener
import com.sapient.recipeapp.ui.model.RecipeUiState

class RecipeListAdapter(
    private val recyclerItemListener: RecipeListActionListener
) : ListAdapter<RecipeUiState, RecipeListViewHolder>(RecipeDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        val itemBinding =
            ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeListViewHolder(itemBinding, recyclerItemListener)
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}