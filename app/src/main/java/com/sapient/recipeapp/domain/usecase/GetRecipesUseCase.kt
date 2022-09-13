package com.sapient.recipeapp.domain.usecase

import com.sapient.recipeapp.data.Resource
import com.sapient.recipeapp.domain.model.RecipeDomainModel
import com.sapient.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val repository: RecipeRepository) {
    operator fun invoke(): Flow<Resource<List<RecipeDomainModel>>> = repository.requestRecipes()
}