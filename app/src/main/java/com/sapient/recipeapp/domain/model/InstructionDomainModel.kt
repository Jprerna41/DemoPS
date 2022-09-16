package com.sapient.recipeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InstructionDomainModel(
    val name: String?,
    val steps: List<StepsDomainModel>?
) :DomainModel(), Parcelable