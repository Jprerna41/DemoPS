package com.sapient.recipeapp.domain.utils

// A generic class that contains data and status about loading api data.
sealed class Resource<T> {
    data class Success<T>(val data: T?) : Resource<T>()
    data class Loading<T>(val data: T? = null) : Resource<T>()
    data class Error<T>(val message: String?, val data: T? = null) : Resource<T>()
}
