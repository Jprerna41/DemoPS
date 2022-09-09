package com.sapient.recipeapp.data.remote

import android.content.Context
import com.sapient.recipeapp.R
import com.sapient.recipeapp.data.model.RecipeEntity
import com.sapient.recipeapp.data.remote.api.RecipeApi
import com.sapient.recipeapp.data.remote.network.ApiResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: RecipeApi,
    @ApplicationContext private val context: Context
) {
    private val apiKey = context.getString(R.string.api_key)
    suspend fun getRecipes(): Flow<ApiResponse<List<RecipeEntity>>> {
        return flow {
            try {
                val response = apiService.getRecipes(apiKey, true)
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