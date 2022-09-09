package com.sapient.recipeapp.presentation.model

import android.os.Parcelable
import com.sapient.recipeapp.presentation.base.ModelItem
import kotlinx.parcelize.Parcelize


@Parcelize
data class RecipeItem(
    val id: Int,
    val title: String?,
    val summary: String?,
    val imageUrl: String?,
    val imageType: String?,
    val sourceName: String?,
    val dishTypes: List<String>?,
    val analyzedInstructions: List<InstructionItem>?,
    var isFavourite: Boolean = false
) : ModelItem(), Parcelable
