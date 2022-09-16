package com.sapient.recipeapp.ui.base

interface UiStateMapper<T, UiStateModel> {
    fun mapToDomainModel(uiModel: UiStateModel): T
    fun mapToUiModel(domainModel: T): UiStateModel
}