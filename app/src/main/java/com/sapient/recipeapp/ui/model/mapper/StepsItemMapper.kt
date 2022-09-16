package com.sapient.recipeapp.ui.model.mapper

import com.sapient.recipeapp.domain.model.StepsDomainModel
import com.sapient.recipeapp.ui.base.UiStateMapper
import com.sapient.recipeapp.ui.model.StepsUiState
import javax.inject.Inject

class StepsItemMapper @Inject constructor(private val ingredientEntityMapper: IngredientItemMapper) :
    UiStateMapper<StepsDomainModel, StepsUiState> {
    override fun mapToDomainModel(uiModel: StepsUiState) = StepsDomainModel(
        number = uiModel.number,
        step = uiModel.step,
        ingredients = uiModel.ingredients?.map { ingredientEntityMapper.mapToDomainModel(it) }
    )

    override fun mapToUiModel(domainModel: StepsDomainModel) = StepsUiState(
        number = domainModel.number,
        step = domainModel.step,
        ingredients = domainModel.ingredients?.map { ingredientEntityMapper.mapToUiModel(it) }
    )
}