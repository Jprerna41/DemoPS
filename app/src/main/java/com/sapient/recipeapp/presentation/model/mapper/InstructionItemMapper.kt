package com.sapient.recipeapp.presentation.model.mapper

import com.sapient.recipeapp.domain.model.InstructionDomainModel
import com.sapient.recipeapp.presentation.base.ItemMapper
import com.sapient.recipeapp.presentation.model.InstructionItem
import javax.inject.Inject

class InstructionItemMapper @Inject constructor(private val stepsEntityMapper: StepsItemMapper) :
    ItemMapper<InstructionDomainModel, InstructionItem> {

    override fun mapToPresentation(model: InstructionDomainModel) = InstructionItem(
        name = model.name,
        steps = model.steps?.map { stepsEntityMapper.mapToPresentation(it) }
    )

    override fun mapToDomain(modelItem: InstructionItem) = InstructionDomainModel(
        name = modelItem.name,
        steps = modelItem.steps?.map { stepsEntityMapper.mapToDomain(it) }
    )
}