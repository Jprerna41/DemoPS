package com.sapient.recipeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeItem(
    val id: Int,
    val title: String,
    val summary: String,
    val imageUrl: String,
    val imageType: String,
    val sourceName: String,
    val dishTypes: List<String>,
    val analyzedInstructions: List<Instruction>,
    val steps: List<Step>,
    val ingredients: List<Ingredient>,
    var isFavourite : Boolean = false
) : Parcelable {
    override fun toString() = title
}

@Parcelize
data class Instruction(
    val name: String,
    val steps: List<Step>
) : Parcelable

@Parcelize
data class Step(
    val number: Int,
    val step: String,
    val ingredients: List<Ingredient>
) : Parcelable

@Parcelize
data class Ingredient(
    val id: Int,
    val name: String,
    val localizedName: String,
    val image: String,
) : Parcelable