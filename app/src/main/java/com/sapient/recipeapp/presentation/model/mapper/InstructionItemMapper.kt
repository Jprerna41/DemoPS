package com.sapient.recipeapp.presentation.model.mapper

import com.sapient.recipeapp.domain.model.Instruction
import com.sapient.recipeapp.presentation.base.ItemMapper
import com.sapient.recipeapp.presentation.model.InstructionItem
import javax.inject.Inject

class InstructionItemMapper @Inject constructor(private val stepsEntityMapper: StepsItemMapper) :
    ItemMapper<Instruction, InstructionItem> {

    override fun mapToPresentation(model: Instruction) = InstructionItem(
        name = model.name,
        steps = model.steps!!.map { stepsEntityMapper.mapToPresentation(it) }
    )

    override fun mapToDomain(modelItem: InstructionItem) = Instruction(
        name = modelItem.name,
        steps = modelItem.steps!!.map { stepsEntityMapper.mapToDomain(it) }
    )
}