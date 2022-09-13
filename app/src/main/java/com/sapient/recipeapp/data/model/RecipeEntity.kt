package com.sapient.recipeapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sapient.recipeapp.data.base.ModelEntity

@Entity(tableName = RecipeEntity.TABLE_NAME)
data class RecipeEntity(

    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,

    @ColumnInfo(name = COLUMN_TITLE)
    val title: String?,

    @ColumnInfo(name = COLUMN_SUMMARY)
    val summary: String?,

    @ColumnInfo(name = COLUMN_IMAGE_URL)
    val image: String?,

    @ColumnInfo(name = COLUMN_IMAGE_TYPE)
    val imageType: String?,

    @ColumnInfo(name = COLUMN_SOURCE_NAME)
    val sourceName: String?,

    @ColumnInfo(name = COLUMN_DISH_TYPES)
    val dishTypes: List<String>?,

    @ColumnInfo(name = COLUMN_ANALYZED_INSTRUCTIONS)
    val analyzedInstructions: List<InstructionEntity>?,

    var isFavourite: Boolean = false

) : ModelEntity() {
    companion object {
        const val TABLE_NAME = "favorite"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_SUMMARY = "summary"
        const val COLUMN_IMAGE_URL = "image_url"
        const val COLUMN_IMAGE_TYPE = "image_type"
        const val COLUMN_SOURCE_NAME = "source_name"
        const val COLUMN_DISH_TYPES = "dish_types"
        const val COLUMN_ANALYZED_INSTRUCTIONS = "analyzed_instructions"
    }
}