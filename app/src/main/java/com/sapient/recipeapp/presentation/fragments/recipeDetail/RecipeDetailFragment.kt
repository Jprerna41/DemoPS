package com.sapient.recipeapp.presentation.fragments.recipeDetail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sapient.recipeapp.databinding.FragmentRecipeDetailBinding
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.presentation.base.BaseFragment
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
        return FragmentRecipeDetailBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showData(args.recipeItem)
    }

    private fun showData(recipe: RecipeItem) {
        binding.tvTitle.text = recipe.title
        binding.tvSummary.setTextFromHtml(recipe.summary)
        binding.tvIngredients.text =
            recipeDetailViewModel.getFormattedIngredients(recipe.ingredients)
        binding.tvSteps.text = recipeDetailViewModel.getFormattedSteps(recipe.steps)
        binding.imgCover.loadRecipeImage(
            recipe.imageUrl
        )

        if (recipe.ingredients.isEmpty()) {
            binding.tvHintIngredients.visibility = View.GONE
            binding.tvIngredients.visibility = View.GONE
        }

        if (recipe.steps.isEmpty()) {
            binding.tvHintSteps.visibility = View.GONE
            binding.tvSteps.visibility = View.GONE
        }
    }
}