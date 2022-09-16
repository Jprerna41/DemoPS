package com.sapient.recipeapp.data.mapper

import com.sapient.recipeapp.data.base.EntityMapper
import com.sapient.recipeapp.data.model.IngredientEntity
import com.sapient.recipeapp.domain.model.IngredientDomainModel
import javax.inject.Inject

class IngredientEntityMapper @Inject constructor() :
    EntityMapper<IngredientDomainModel, IngredientEntity> {
    override fun mapToDomainModel(uiModel: IngredientEntity) = IngredientDomainModel(
        id = uiModel.id,
        name = uiModel.name,
        localizedName = uiModel.localizedName,
        image = uiModel.image
    )

    override fun mapToDataModel(domainModel: IngredientDomainModel) = IngredientEntity(
        id = domainModel.id,
        name = domainModel.name,
        localizedName = domainModel.localizedName,
        image = domainModel.image
    )
}