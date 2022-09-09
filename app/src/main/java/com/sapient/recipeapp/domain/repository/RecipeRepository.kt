package com.sapient.recipeapp.domain.repository

import com.sapient.recipeapp.data.Resource
import com.sapient.recipeapp.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun requestRecipes(): Flow<Resource<List<Recipe>>>

    fun insertFavorite(recipe: Recipe)

    fun deleteFavorite(recipe: Recipe)
}