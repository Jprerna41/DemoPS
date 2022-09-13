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

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRecipeListBinding {
        return FragmentRecipeListBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recipeListViewModel.getRecipes()
        super.onViewCreated(view, savedInstanceState)

        binding.rvRecipes.apply {
            recipeListAdapter = RecipeListAdapter(this@RecipeListFragment)
            adapter = recipeListAdapter
            layoutManager = GridLayoutManager(context, 2)
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
            setHasFixedSize(true)
        }

        initObserver()
    }

    private fun initObserver() = with(recipeListViewModel) {
        recipesData.observe(viewLifecycleOwner) { data ->
            bindListData(data)
        }

        pbLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) binding.pbLoading.visible()
            else binding.pbLoading.gone()
        }
    }

    private fun bindListData(recipes: List<RecipeItem>?) =
        if (recipes.isNullOrEmpty()) binding.tvNotFound.visible()
        else recipeListAdapter.submitList(recipes)


    override fun onItemSelected(recipe: RecipeItem) {
        val action = RecipeListFragmentDirections.actionNavigateToDetailView(recipe)
        findNavController().navigate(action)
    }

    override fun onUpdateFavourite(recipesItem: RecipeItem, position: Int) {
        with(recipeListViewModel) {
            if (recipesItem.isFavourite) addFavourite(recipesItem) else removeFavourite(recipesItem)
        }.also { recipeListAdapter.notifyItemChanged(position) }
    }
}