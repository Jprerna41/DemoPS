package com.sapient.recipeapp.data.remote.response

import com.google.gson.annotations.SerializedName
import com.sapient.recipeapp.data.model.RecipeEntity

data class ListRecipeResponse(
    @SerializedName(value = "results")
    val results: List<RecipeEntity>
)