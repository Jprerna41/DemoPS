package com.sapient.recipeapp.data.remote.api

import com.sapient.recipeapp.data.remote.response.ListRecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Api services to communicate with server
 */

interface RecipeApi {

    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("apiKey") apiKey: String,
        @Query("addRecipeInformation") addRecipeInformation: Boolean? = true
    ): ListRecipeResponse
}
