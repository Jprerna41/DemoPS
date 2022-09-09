package com.sapient.recipeapp.data.model

import com.google.gson.annotations.SerializedName
import com.sapient.recipeapp.data.base.ModelEntity

data class StepEntity(

    @SerializedName(value = "number")
    val number: Int,

    @SerializedName(value = "step")
    val step: String?,

    @SerializedName(value = "ingredients")
    val ingredients: List<IngredientEntity>?
) : ModelEntity()