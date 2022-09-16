package com.sapient.recipeapp.data.mapper

import com.sapient.recipeapp.data.base.EntityMapper
import com.sapient.recipeapp.data.model.StepEntity
import com.sapient.recipeapp.domain.model.StepsDomainModel
import javax.inject.Inject

class StepsEntityMapper @Inject constructor(private val ingredientEntityMapper: IngredientEntityMapper) :
    EntityMapper<StepsDomainModel, StepEntity> {
    override fun mapToDomainModel(uiModel: StepEntity) = StepsDomainModel(
        number = uiModel.number,
        step = uiModel.step,
        ingredients = uiModel.ingredients?.map { ingredientEntityMapper.mapToDomainModel(it) }
    )

    override fun mapToDataModel(domainModel: StepsDomainModel) = StepEntity(
        number = domainModel.number,
        step = domainModel.step,
        ingredients = domainModel.ingredients?.map { ingredientEntityMapper.mapToDataModel(it) }
    )
}