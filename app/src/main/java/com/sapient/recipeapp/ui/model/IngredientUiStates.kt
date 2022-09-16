package com.sapient.recipeapp.ui.model

import android.os.Parcelable
import com.sapient.recipeapp.ui.base.UiStateModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientUiStates(
    val id: Int?,
    val name: String?,
    val localizedName: String?,
    val image: String?,
) : UiStateModel(), Parcelable