package com.sapient.recipeapp.data

import com.sapient.recipeapp.data.local.LocalDataSource
import com.sapient.recipeapp.data.mapper.RecipeEntityMapper
import com.sapient.recipeapp.data.model.RecipeEntity
import com.sapient.recipeapp.data.remote.RemoteDataSource
import com.sapient.recipeapp.data.remote.network.ApiResponse
import com.sapient.recipeapp.domain.model.Recipe
import com.sapient.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val mapper: RecipeEntityMapper
) : RecipeRepository {

    override fun requestRecipes(): Flow<Resource<List<Recipe>>> {
        return flow {
            emitAll(remoteDataSource.getRecipes().map {
                when (it) {
                    is ApiResponse.Success -> {
                        Resource.Success(it.data.map { recipeResponse ->
                            recipeResponse.isFavourite = getIsFavouriteFromDb(recipeResponse)
                            mapper.mapToDomain(recipeResponse)
                        })
                    }
                    is ApiResponse.Empty -> {
                        Resource.Success(emptyList())
                    }
                    is ApiResponse.Error -> {
                        Resource.Error(it.errorMessage)
                    }
                }
            })
        }
    }

    private suspend fun getIsFavouriteFromDb(recipeResponse: RecipeEntity): Boolean {
        return localDataSource.getFavorite(recipeResponse.id).first() != null
    }

    override fun insertFavorite(recipe: Recipe) {
        localDataSource.insertFavorite(mapper.mapToEntity(recipe))
    }

    override fun deleteFavorite(recipe: Recipe) {
        localDataSource.removeFavourite(mapper.mapToEntity(recipe))
    }

}