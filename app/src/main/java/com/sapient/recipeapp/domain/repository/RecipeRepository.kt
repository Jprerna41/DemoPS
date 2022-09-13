package com.sapient.recipeapp.domain.repository

import com.sapient.recipeapp.data.Resource
import com.sapient.recipeapp.domain.model.RecipeDomainModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun requestRecipes(): Flow<Resource<List<RecipeDomainModel>>>

    fun insertFavorite(recipe: RecipeDomainModel)

    fun deleteFavorite(recipe: RecipeDomainModel)
}