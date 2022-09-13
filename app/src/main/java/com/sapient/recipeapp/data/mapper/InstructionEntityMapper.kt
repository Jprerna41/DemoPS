package com.sapient.recipeapp.data.mapper

import com.sapient.recipeapp.data.base.EntityMapper
import com.sapient.recipeapp.data.model.InstructionEntity
import com.sapient.recipeapp.domain.model.InstructionDomainModel
import javax.inject.Inject

class InstructionEntityMapper @Inject constructor(private val stepsEntityMapper: StepsEntityMapper) :
    EntityMapper<InstructionDomainModel, InstructionEntity> {
    override fun mapToDomain(entity: InstructionEntity) = InstructionDomainModel(
        name = entity.name,
        steps = entity.steps?.map { stepsEntityMapper.mapToDomain(it) }
    )

    override fun mapToEntity(model: InstructionDomainModel) = InstructionEntity(
        name = model.name,
        steps = model.steps?.map { stepsEntityMapper.mapToEntity(it) }
    )
}