package com.sapient.recipeapp.data.remote

import com.sapient.recipeapp.data.remote.api.RecipeApi
import com.sapient.recipeapp.data.remote.network.ApiResponse
import com.sapient.recipeapp.data.remote.response.RecipeResponse
import com.sapient.recipeapp.utils.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: RecipeApi) {

    suspend fun getRecipes(): Flow<ApiResponse<List<RecipeResponse>>> {
        return flow {
            try {
                val response = apiService.getRecipes(API_KEY, true)
                val dataArray = response.results

                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}