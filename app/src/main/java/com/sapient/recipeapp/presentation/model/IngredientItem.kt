package com.sapient.recipeapp.presentation.model

import android.os.Parcelable
import com.sapient.recipeapp.presentation.base.ModelItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientItem(
    val id: Int?,
    val name: String?,
    val localizedName: String?,
    val image: String?,
) : ModelItem(), Parcelable