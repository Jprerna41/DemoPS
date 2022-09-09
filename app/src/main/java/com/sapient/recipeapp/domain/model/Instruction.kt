package com.sapient.recipeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Instruction(
    val name: String?,
    val steps: List<Steps>?
) :Model(), Parcelable