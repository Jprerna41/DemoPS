package com.sapient.recipeapp.data.model

import androidx.room.ColumnInfo
import com.sapient.recipeapp.data.base.EntityModel

data class StepEntity(

    @ColumnInfo(name = COLUMN_NUMBER)
    val number: Int,

    @ColumnInfo(name = COLUMN_STEP)
    val step: String?,

    @ColumnInfo(name = COLUMN_INGREDIENTS)
    val ingredients: List<IngredientEntity>?

) : EntityModel(){
    companion object {
        const val COLUMN_NUMBER = "number"
        const val COLUMN_STEP = "step"
        const val COLUMN_INGREDIENTS = "ingredients"
    }
}