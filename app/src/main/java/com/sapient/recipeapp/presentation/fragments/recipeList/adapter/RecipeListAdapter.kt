package com.sapient.recipeapp.presentation.fragments.recipeList.adapter

import android.graphics.Color.red
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sapient.recipeapp.R
import com.sapient.recipeapp.databinding.ItemRecipeBinding
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.utils.extensions.loadRecipeImage

class RecipeListAdapter(
    private val recyclerItemListener: OnItemClickListener,
    private val recipes: ArrayList<RecipeItem>
) : ListAdapter<RecipeItem, RecipeListAdapter.RecipeListViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        val itemBinding =
            ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeListViewHolder(itemBinding)
    }

    class RecipeListViewHolder(
        private val itemBinding: ItemRecipeBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(recipesItem: RecipeItem, recyclerItemListener: OnItemClickListener) {
            if (recipesItem.imageUrl.isNotBlank() || recipesItem.imageUrl.isNotEmpty()) {
                itemBinding.tvTitle.text = recipesItem.title
                itemBinding.tvAuthor.text = recipesItem.sourceName
                itemBinding.imgCover.loadRecipeImage(recipesItem.imageUrl)
                itemBinding.recipeItem.setOnClickListener {
                    recyclerItemListener.onItemSelected(
                        recipesItem
                    )
                }
                itemBinding.btnFavorite.setOnClickListener {
                    recipesItem.isFav = true
                    itemBinding.btnFavorite.setIconTintResource(R.color.colorRed)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.bind(recipes[position], recyclerItemListener)
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<RecipeItem?>() {
        override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
            return oldItem == newItem
        }
    }

}

interface OnItemClickListener {
    fun onItemSelected(recipe: RecipeItem)
}