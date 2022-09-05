package com.sapient.recipeapp.domain.repository

import com.sapient.recipeapp.data.Result
import com.sapient.recipeapp.domain.model.RecipeItem
import kotlinx.coroutines.flow.Flow

interface IRecipeRepository {

    fun requestRecipes(): Flow<Result<List<RecipeItem>>>

    fun isFavorite(recipe: RecipeItem): Flow<Result<Boolean>>

    fun insertFavorite(recipe: RecipeItem)

    fun deleteFavorite(recipe: RecipeItem)
}