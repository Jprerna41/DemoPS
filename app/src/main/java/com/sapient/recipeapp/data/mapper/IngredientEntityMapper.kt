package com.sapient.recipeapp.data.mapper

import com.sapient.recipeapp.data.base.EntityMapper
import com.sapient.recipeapp.data.model.IngredientEntity
import com.sapient.recipeapp.domain.model.IngredientDomainModel
import javax.inject.Inject

class IngredientEntityMapper @Inject constructor() : EntityMapper<IngredientDomainModel, IngredientEntity> {
    override fun mapToDomain(entity: IngredientEntity) = IngredientDomainModel(
        id = entity.id,
        name = entity.name,
        localizedName = entity.localizedName,
        image = entity.image
    )

    override fun mapToEntity(model: IngredientDomainModel) = IngredientEntity(
        id = model.id,
        name = model.name,
        localizedName = model.localizedName,
        image = model.image
    )
}