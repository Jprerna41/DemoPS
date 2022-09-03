package com.sapient.recipeapp.presentation.fragments.recipeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.sapient.recipeapp.data.Result
import com.sapient.recipeapp.databinding.FragmentRecipeListBinding
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.presentation.base.BaseFragment
import com.sapient.recipeapp.presentation.fragments.recipeList.adapter.OnItemClickListener
import com.sapient.recipeapp.presentation.fragments.recipeList.adapter.RecipeListAdapter
import com.sapient.recipeapp.utils.extensions.gone
import com.sapient.recipeapp.utils.extensions.toast
import com.sapient.recipeapp.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : BaseFragment<FragmentRecipeListBinding>(), OnItemClickListener {

    private val recipeListViewModel: RecipeListViewModel by viewModels()
    private lateinit var recipeListAdapter: RecipeListAdapter

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRecipeListBinding {
        return FragmentRecipeListBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = GridLayoutManager(context, 2)
        binding.rvRecipes.layoutManager = layoutManager
        binding.rvRecipes.setHasFixedSize(true)
        recipeListAdapter = RecipeListAdapter(this)
        binding.rvRecipes.adapter = recipeListAdapter
        (binding.rvRecipes.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        recipeListViewModel.getRecipes()
        initObserver()
    }

    private fun initObserver() {
        showLoadingContent()
        recipeListViewModel.recipesLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> showLoadingContent()
                is Result.Success -> result.data?.let { bindListData(it) }
                is Result.Error -> {
                    showDataView(false)
                    result.toast(context)
                }
            }
        }
    }

    private fun showLoadingContent() {
        binding.pbLoading.visible()
        binding.tvNotFound.gone()
        binding.rvRecipes.gone()
    }

    private fun showDataView(show: Boolean) {
        binding.tvNotFound.visibility = if (show) View.GONE else View.VISIBLE
        binding.rvRecipes.visibility = if (show) View.VISIBLE else View.GONE
        binding.pbLoading.gone()
    }

    private fun bindListData(recipes: List<RecipeItem>) {
        if (recipes.isNotEmpty()) {
            recipeListAdapter.submitList(recipes)
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    override fun onItemSelected(recipe: RecipeItem) {
        val action = RecipeListFragmentDirections.actionNavigateToDetailView(recipe)
        findNavController().navigate(action)
    }

    override fun onUpdateFavourite(recipesItem: RecipeItem, position: Int) {
        recipeListViewModel.setFavorite(recipesItem)
        recipeListAdapter.notifyItemChanged(position)
    }


}