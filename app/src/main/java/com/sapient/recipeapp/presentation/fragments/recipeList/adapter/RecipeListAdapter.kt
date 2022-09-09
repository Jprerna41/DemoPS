package com.sapient.recipeapp.presentation.fragments.recipeList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sapient.recipeapp.R
import com.sapient.recipeapp.databinding.ItemRecipeBinding
import com.sapient.recipeapp.presentation.fragments.recipeList.RecipeListActionListener
import com.sapient.recipeapp.presentation.model.RecipeItem
import com.sapient.recipeapp.utils.extensions.loadRecipeImage

class RecipeListAdapter(
    private val recyclerItemListener: RecipeListActionListener
) : ListAdapter<RecipeItem, RecipeListAdapter.RecipeListViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        val itemBinding =
            ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeListViewHolder(itemBinding)
    }

    inner class RecipeListViewHolder(
        private val itemBinding: ItemRecipeBinding,
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(recipesItem: RecipeItem) {
            itemBinding
                .apply {
                    tvTitle.text = recipesItem.title
                    tvAuthor.text = recipesItem.sourceName
                    imgCover.loadRecipeImage(recipesItem.imageUrl)
                    recipeItem.setOnClickListener {
                        recyclerItemListener.onItemSelected(recipesItem)
                    }
                    btnFavorite.apply {
                        setIconTintResource(getArchiveIconTint(recipesItem.isFavourite))
                        setOnClickListener {
                            recipesItem.isFavourite = !recipesItem.isFavourite
                            recyclerItemListener.onUpdateFavourite(
                                recipesItem,
                                adapterPosition
                            )
                        }
                    }
                }
        }

        private fun getArchiveIconTint(isFavorite: Boolean): Int {
            return if (isFavorite) R.color.colorRed
            else R.color.colorBoulder
        }
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.bind(getItem(position))
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