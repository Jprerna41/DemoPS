package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.domain.model.Recipe
import com.sapient.recipeapp.domain.repository.RecipeRepository
import javax.inject.Inject

class FavouriteRecipesUseCase @Inject constructor(private val repository: RecipeRepository) {

    fun insertFavorite(recipe: Recipe) = repository.insertFavorite(recipe)

    fun deleteFavorite(recipe: Recipe) = repository.deleteFavorite(recipe)
}