package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.data.Result
import com.sapient.recipeapp.domain.model.RecipeItem
import kotlinx.coroutines.flow.Flow

interface ICoreUseCase {

    fun getRecipes(): Flow<Result<List<RecipeItem>>>

    fun isFavorite(recipe: RecipeItem): Flow<Result<Boolean>>

    fun insertFavorite(recipe: RecipeItem)

    fun deleteFavorite(recipe: RecipeItem)
}