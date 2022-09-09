package com.sapient.recipeapp.presentation.model

import android.os.Parcelable
import com.sapient.recipeapp.presentation.base.ModelItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class InstructionItem(
    val name: String?,
    val steps: List<StepsItem>?
) : ModelItem(), Parcelable