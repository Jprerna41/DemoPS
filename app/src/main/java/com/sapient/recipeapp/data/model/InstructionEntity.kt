package com.sapient.recipeapp.data.model

import com.google.gson.annotations.SerializedName
import com.sapient.recipeapp.data.base.ModelEntity

data class InstructionEntity(
    @SerializedName(value = "name")
    val name: String?,

    @SerializedName(value = "steps")
    val steps: List<StepEntity>?
) : ModelEntity()