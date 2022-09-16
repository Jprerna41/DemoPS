package com.sapient.recipeapp.data.model

import androidx.room.ColumnInfo
import com.sapient.recipeapp.data.base.EntityModel

data class IngredientEntity(
    @ColumnInfo(name = COLUMN_ID)
    val id: Int?,

    @ColumnInfo(name = COLUMN_NAME)
    val name: String?,

    @ColumnInfo(name = COLUMN_LOCALIZED_NAME)
    val localizedName: String?,

    @ColumnInfo(name = COLUMN_IMAGE)
    val image: String?,

) : EntityModel() {
    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_LOCALIZED_NAME = "localized_name"
        const val COLUMN_IMAGE = "image"
    }
}