package com.sapient.recipeapp.data

import com.sapient.recipeapp.domain.model.RecipeItem
import kotlinx.coroutines.flow.Flow

interface IRecipeRepository {
    suspend fun requestRecipes(): Flow<List<RecipeItem>>
}
