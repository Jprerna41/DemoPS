package com.sapient.recipeapp.data.mapper

import com.sapient.recipeapp.data.base.EntityMapper
import com.sapient.recipeapp.data.model.StepEntity
import com.sapient.recipeapp.domain.model.StepsDomainModel
import javax.inject.Inject

class StepsEntityMapper @Inject constructor(private val ingredientEntityMapper: IngredientEntityMapper) :
    EntityMapper<StepsDomainModel, StepEntity> {
    override fun mapToDomain(entity: StepEntity) = StepsDomainModel(
        number = entity.number,
        step = entity.step,
        ingredients = entity.ingredients?.map { ingredientEntityMapper.mapToDomain(it) }
    )

    override fun mapToEntity(model: StepsDomainModel) = StepEntity(
        number = model.number,
        step = model.step,
        ingredients = model.ingredients?.map { ingredientEntityMapper.mapToEntity(it) }
    )
}