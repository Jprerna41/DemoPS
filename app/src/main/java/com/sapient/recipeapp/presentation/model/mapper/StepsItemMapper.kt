package com.sapient.recipeapp.presentation.model.mapper

import com.sapient.recipeapp.domain.model.Steps
import com.sapient.recipeapp.presentation.base.ItemMapper
import com.sapient.recipeapp.presentation.model.StepsItem
import javax.inject.Inject

class StepsItemMapper @Inject constructor(private val ingredientEntityMapper: IngredientItemMapper) :
    ItemMapper<Steps, StepsItem> {
    override fun mapToDomain(modelItem: StepsItem) = Steps(
        number = modelItem.number,
        step = modelItem.step,
        ingredients = modelItem.ingredients?.map { ingredientEntityMapper.mapToDomain(it) }
    )

    override fun mapToPresentation(model: Steps) = StepsItem(
        number = model.number,
        step = model.step,
        ingredients = model.ingredients?.map { ingredientEntityMapper.mapToPresentation(it) }
    )

}