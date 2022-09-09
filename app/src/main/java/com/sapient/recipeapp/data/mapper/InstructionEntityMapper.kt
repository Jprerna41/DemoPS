package com.sapient.recipeapp.data.mapper

import com.sapient.recipeapp.data.base.EntityMapper
import com.sapient.recipeapp.data.model.InstructionEntity
import com.sapient.recipeapp.domain.model.Instruction
import javax.inject.Inject

class InstructionEntityMapper @Inject constructor(private val stepsEntityMapper: StepsEntityMapper) :
    EntityMapper<Instruction, InstructionEntity> {
    override fun mapToDomain(entity: InstructionEntity) = Instruction(
        name = entity.name,
        steps = entity.steps!!.map { stepsEntityMapper.mapToDomain(it) }
    )

    override fun mapToEntity(model: Instruction) = InstructionEntity(
        name = model.name,
        steps = model.steps!!.map { stepsEntityMapper.mapToEntity(it) }
    )
}