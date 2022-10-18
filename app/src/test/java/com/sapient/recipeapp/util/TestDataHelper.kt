package com.sapient.recipeapp.util

import com.sapient.recipeapp.domain.model.RecipeDomainModel
import com.sapient.recipeapp.domain.utils.Resource
import com.sapient.recipeapp.util.RecipeDomainDataProvider.Companion.getRecipeDomainList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun getRecipeResponseFromDB(): Flow<Resource<List<RecipeDomainModel>>> {
    return flow {
        val ch = getRecipeDomainList()
        emit(Resource.Success(ch))
    }.flowOn(Dispatchers.IO)
}

fun getEmptyResponseFromDB(): Flow<Resource<List<RecipeDomainModel>>> {
    return flow {
        val ch = emptyList<RecipeDomainModel>()
        emit(Resource.Success(ch))
    }.flowOn(Dispatchers.IO)
}

fun <T> getChampionDetailsResultDataError(): Flow<Resource<T>> {
    return flow { emit(getRecipeListResponseError("Data error")) }
}

fun <T> getRecipeListResponseError(errorMsg: String?): Resource<T> {
    return Resource.Error(errorMsg)
}


