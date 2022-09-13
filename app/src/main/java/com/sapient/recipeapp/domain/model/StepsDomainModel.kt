package com.sapient.recipeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StepsDomainModel(
    val number: Int,
    val step: String?,
    val ingredients: List<IngredientDomainModel>?
) : Model(), Parcelable