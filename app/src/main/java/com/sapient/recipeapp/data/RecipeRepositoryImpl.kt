package com.sapient.recipeapp.data

import com.sapient.recipeapp.data.remote.network.RecipeServices
import com.sapient.recipeapp.data.remote.network.model.RecipeDtoMapper
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.utils.API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class RecipeRepositoryImpl(
    private val recipeService: RecipeServices,
    private val mapper: RecipeDtoMapper,
    ) : IRecipeRepository {

    override suspend fun requestRecipes(): Flow<List<RecipeItem>> {
        val res = mapper.toDomainList(recipeService.getRecipes(API_KEY,true).results)
        return listOf(res).asFlow()
    }

}