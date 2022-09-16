package com.sapient.recipeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeDomainModel(
    val id: Int,
    val title: String?,
    val summary: String?,
    val imageUrl: String?,
    val sourceName: String?,
    val analyzedInstructions: List<InstructionDomainModel>?,
    var isFavourite: Boolean = false
) : DomainModel(), Parcelable