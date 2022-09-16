package com.sapient.recipeapp.data.mapper

import com.sapient.recipeapp.data.base.EntityMapper
import com.sapient.recipeapp.data.model.InstructionEntity
import com.sapient.recipeapp.domain.model.InstructionDomainModel
import javax.inject.Inject

class InstructionEntityMapper @Inject constructor(private val stepsEntityMapper: StepsEntityMapper) :
    EntityMapper<InstructionDomainModel, InstructionEntity> {
    override fun mapToDomainModel(uiModel: InstructionEntity) = InstructionDomainModel(
        name = uiModel.name,
        steps = uiModel.steps?.map { stepsEntityMapper.mapToDomainModel(it) }
    )

    override fun mapToDataModel(domainModel: InstructionDomainModel) = InstructionEntity(
        name = domainModel.name,
        steps = domainModel.steps?.map { stepsEntityMapper.mapToDataModel(it) }
    )
}