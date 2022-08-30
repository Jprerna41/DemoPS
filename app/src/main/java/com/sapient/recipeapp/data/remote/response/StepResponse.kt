package com.sapient.recipeapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class StepResponse(

    @SerializedName(value = "number")
    val number: Int?,

    @SerializedName(value = "step")
    val step: String?,

    @SerializedName(value = "ingredients")
    val ingredients: List<IngredientResponse>?
)