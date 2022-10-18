package com.sapient.recipeapp.domain.utils

// A generic class that contains data and status about loading api data.
sealed class Resource<T>(val data: T?, val errorMessage: String?) {
    class Success<T>( data: T?) : Resource<T>(data,null)
    class Loading<T> : Resource<T>(null,null)
    class Error<T>(errorMessage: String?) : Resource<T>(null,errorMessage)
}
