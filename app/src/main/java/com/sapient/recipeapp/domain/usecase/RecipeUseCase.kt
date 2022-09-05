package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.data.Result
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.domain.repository.IRecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeUseCase @Inject constructor(private val repository: IRecipeRepository) : IRecipeUseCase {

    override fun getRecipes(): Flow<Result<List<RecipeItem>>> =
        repository.requestRecipes()

    override fun isFavorite(recipe: RecipeItem): Flow<Result<Boolean>> =
        repository.isFavorite(recipe)

    override fun insertFavorite(recipe: RecipeItem) = repository.insertFavorite(recipe)

    override fun deleteFavorite(recipe: RecipeItem) = repository.deleteFavorite(recipe)
}