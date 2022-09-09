package com.sapient.recipeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Ingredient(
    val id: Int,
    val name: String?,
    val localizedName: String?,
    val image: String?,
) : Model(), Parcelable