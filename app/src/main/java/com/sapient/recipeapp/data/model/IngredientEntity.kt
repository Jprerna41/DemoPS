package com.sapient.recipeapp.data.model

import com.google.gson.annotations.SerializedName
import com.sapient.recipeapp.data.base.ModelEntity

data class IngredientEntity(
    @SerializedName(value = "id")
    val id: Int,

    @SerializedName(value = "name")
    val name: String?,

    @SerializedName(value = "localizedName")
    val localizedName: String?,

    @SerializedName(value = "image")
    val image: String?,
) : ModelEntity()