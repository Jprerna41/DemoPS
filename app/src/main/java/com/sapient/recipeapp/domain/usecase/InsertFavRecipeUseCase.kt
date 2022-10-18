package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.domain.model.RecipeDomainModel
import com.sapient.recipeapp.domain.repository.RecipeRepository
import javax.inject.Inject

class InsertFavRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(recipe: RecipeDomainModel) = repository.insertFavorite(recipe)
}