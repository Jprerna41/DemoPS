package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.data.Resource
import com.sapient.recipeapp.domain.model.Recipe
import com.sapient.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val repository: RecipeRepository) {

    fun getRecipes(): Flow<Resource<List<Recipe>>> =
        repository.requestRecipes()

}