package com.sapient.recipeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Steps(
    val number: Int,
    val step: String?,
    val ingredients: List<Ingredient>?
) : Model(), Parcelable