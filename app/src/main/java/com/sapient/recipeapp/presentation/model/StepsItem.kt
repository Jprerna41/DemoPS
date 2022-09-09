package com.sapient.recipeapp.presentation.model

import android.os.Parcelable
import com.sapient.recipeapp.presentation.base.ModelItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class StepsItem(
    val number: Int,
    val step: String?,
    val ingredients: List<IngredientItem>?
) : ModelItem(), Parcelable