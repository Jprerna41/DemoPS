package com.sapient.recipeapp.data.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import com.sapient.recipeapp.data.base.ModelEntity

data class InstructionEntity(

    @ColumnInfo(name = COLUMN_NAME)
    val name: String?,

    @ColumnInfo(name = COLUMN_STEPS)
    val steps: List<StepEntity>?

) : ModelEntity(){
    companion object {
        const val COLUMN_NAME = "name"
        const val COLUMN_STEPS = "steps"
    }
}