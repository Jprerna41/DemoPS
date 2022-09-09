package com.sapient.recipeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id: Int,
    val title: String?,
    val summary: String?,
    val imageUrl: String?,
    val imageType: String?,
    val sourceName: String?,
    val dishTypes: List<String>?,
    val analyzedInstructions: List<Instruction>?,
    val isFavourite: Boolean = false
) : Model(), Parcelable