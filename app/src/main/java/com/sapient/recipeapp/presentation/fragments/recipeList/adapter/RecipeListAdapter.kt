package com.sapient.recipeapp.presentation.fragments.recipeList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sapient.recipeapp.databinding.ItemRecipeBinding
import com.sapient.recipeapp.presentation.fragments.recipeList.RecipeListActionListener
import com.sapient.recipeapp.presentation.model.RecipeItem

class RecipeListAdapter(
    private val recyclerItemListener: RecipeListActionListener
) : ListAdapter<RecipeItem, RecipeListViewHolder>(RecipeDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        val itemBinding =
            ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeListViewHolder(itemBinding, recyclerItemListener)
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}