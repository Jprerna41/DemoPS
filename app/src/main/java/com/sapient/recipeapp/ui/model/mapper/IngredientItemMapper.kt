package com.sapient.recipeapp.ui.model.mapper

import com.sapient.recipeapp.domain.model.IngredientDomainModel
import com.sapient.recipeapp.ui.base.UiStateMapper
import com.sapient.recipeapp.ui.model.IngredientUiStates
import javax.inject.Inject

class IngredientItemMapper @Inject constructor() :
    UiStateMapper<IngredientDomainModel, IngredientUiStates> {
    override fun mapToDomainModel(uiModel: IngredientUiStates) = IngredientDomainModel(
        id = uiModel.id,
        name = uiModel.name,
        localizedName = uiModel.localizedName,
        image = uiModel.image
    )

    override fun mapToUiModel(domainModel: IngredientDomainModel) = IngredientUiStates(
        id = domainModel.id,
        name = domainModel.name,
        localizedName = domainModel.localizedName,
        image = domainModel.image
    )
}