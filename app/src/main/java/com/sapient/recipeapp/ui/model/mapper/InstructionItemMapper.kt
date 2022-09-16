package com.sapient.recipeapp.ui.model.mapper

import com.sapient.recipeapp.domain.model.InstructionDomainModel
import com.sapient.recipeapp.ui.base.UiStateMapper
import com.sapient.recipeapp.ui.model.InstructionUiState
import javax.inject.Inject

class InstructionItemMapper @Inject constructor(private val stepsEntityMapper: StepsItemMapper) :
    UiStateMapper<InstructionDomainModel, InstructionUiState> {
    override fun mapToDomainModel(uiModel: InstructionUiState) = InstructionDomainModel(
        name = uiModel.name,
        steps = uiModel.steps?.map { stepsEntityMapper.mapToDomainModel(it) })

    override fun mapToUiModel(domainModel: InstructionDomainModel) = InstructionUiState(
        name = domainModel.name,
        steps = domainModel.steps?.map { stepsEntityMapper.mapToUiModel(it) })
}