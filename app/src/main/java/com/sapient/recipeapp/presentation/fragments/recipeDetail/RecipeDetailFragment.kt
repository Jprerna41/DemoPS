package com.sapient.recipeapp.presentation.fragments.recipeDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sapient.recipeapp.databinding.FragmentRecipeDetailBinding
import com.sapient.recipeapp.presentation.base.BaseFragment
import com.sapient.recipeapp.presentation.model.RecipeItem
import com.sapient.recipeapp.utils.extensions.gone
import com.sapient.recipeapp.utils.extensions.loadRecipeImage
import com.sapient.recipeapp.utils.extensions.setTextFromHtml
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : BaseFragment<FragmentRecipeDetailBinding>() {

    private val recipeDetailViewModel: RecipeDetailViewModel by viewModels()
    private val args: RecipeDetailFragmentArgs by navArgs()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRecipeDetailBinding {
        recipeDetailViewModel.getIngredientList(args.recipeItem.analyzedInstructions)
        return FragmentRecipeDetailBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showData(args.recipeItem)
    }

    private fun showData(recipe: RecipeItem) {
        binding.apply {
            tvTitle.text = recipe.title
            tvSummary.setTextFromHtml(recipe.summary)
            imgCover.loadRecipeImage(
                recipe.imageUrl
            )
            initObserver()
        }
    }

    private fun initObserver() = with(recipeDetailViewModel) {
        stepsLiveData.observe(viewLifecycleOwner) { steps ->
            if (steps!!.isEmpty()) {
                binding.apply {
                    tvHintSteps.gone()
                    tvSteps.gone()
                }
            }
            binding.tvSteps.text = recipeDetailViewModel.getFormattedSteps(steps)
        }
        ingredientLiveData.observe(viewLifecycleOwner) { ingredients ->
            if (ingredients!!.isEmpty()) {
                binding.apply {
                    tvIngredients.gone()
                    tvHintIngredients.gone()
                }
            }
            binding.tvIngredients.text = recipeDetailViewModel.getFormattedIngredients(ingredients)
        }
    }
}