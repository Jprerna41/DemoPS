package com.sapient.recipeapp.data

import com.sapient.recipeapp.data.local.LocalDataSource
import com.sapient.recipeapp.data.remote.RemoteDataSource
import com.sapient.recipeapp.data.remote.network.ApiResponse
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.domain.repository.ICoreRepository
import com.sapient.recipeapp.utils.DataMapper
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : ICoreRepository{

    override fun requestRecipes(): Flow<Result<List<RecipeItem>>> {
        return flow {
            emit(Result.Loading())
            emitAll(remoteDataSource.getRecipes().map {
                when (it) {
                    is ApiResponse.Success -> {
                        Result.Success(it.data.map { recipeResponse ->
                            val isFavorite =
                                localDataSource.getFavorite(recipeResponse.id).first() != null
                            DataMapper.mapRecipeResponseToDomain(recipeResponse, isFavorite)
                        })
                    }
                    is ApiResponse.Empty -> {
                        Result.Success(emptyList())
                    }
                    is ApiResponse.Error -> {
                        Result.Error(it.errorMessage)
                    }
                }
            })
        }
    }

    override fun isFavorite(recipe: RecipeItem): Flow<Result<Boolean>> {
        return flow {
            localDataSource.insertFavorite(DataMapper.mapRecipeDomainToEntity(recipe))
            emitAll(localDataSource.getFavorite(recipe.id).map {
                Result.Success(it != null)
            })
        }
    }

    override fun insertFavorite(recipe: RecipeItem) {
        localDataSource.insertFavorite(DataMapper.mapRecipeDomainToEntity(recipe))
    }

    override fun deleteFavorite(recipe: RecipeItem) {
        localDataSource.removeFavourite(DataMapper.mapRecipeDomainToEntity(recipe))
    }

}