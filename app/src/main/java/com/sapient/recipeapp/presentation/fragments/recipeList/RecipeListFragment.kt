package com.sapient.recipeapp.presentation.fragments.recipeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.sapient.recipeapp.databinding.FragmentRecipeListBinding
import com.sapient.recipeapp.presentation.base.BaseFragment
import com.sapient.recipeapp.presentation.fragments.recipeList.adapter.RecipeListAdapter
import com.sapient.recipeapp.presentation.model.RecipeItem
import com.sapient.recipeapp.utils.extensions.gone
import com.sapient.recipeapp.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : BaseFragment<FragmentRecipeListBinding>(), RecipeListActionListener {

    private val recipeListViewModel: RecipeListViewModel by viewModels()
    private lateinit var recipeListAdapter: RecipeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipeListViewModel.getRecipes()
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRecipeListBinding {
        return FragmentRecipeListBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridLayoutManager = GridLayoutManager(context, 2)
        recipeListAdapter = RecipeListAdapter(this)

        binding.rvRecipes.apply {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = recipeListAdapter
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }

        initObserver()
    }

    private fun initObserver() = with(recipeListViewModel) {
        recipesLiveData.observe(viewLifecycleOwner) { result ->
            bindListData(result)
        }

        loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.apply {
                    pbLoading.visible()
                    tvNotFound.gone()
                    rvRecipes.gone()
                }
            }
        }
    }

    private fun showDataView(show: Boolean) {
        binding.apply {
            if (show) {
                rvRecipes.visible()
                tvNotFound.gone()
            } else {
                rvRecipes.gone()
                tvNotFound.visible()
            }
            pbLoading.gone()
        }
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