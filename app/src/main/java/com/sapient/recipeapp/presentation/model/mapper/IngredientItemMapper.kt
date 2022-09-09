package com.sapient.recipeapp.presentation.model.mapper

import com.sapient.recipeapp.domain.model.Ingredient
import com.sapient.recipeapp.presentation.base.ItemMapper
import com.sapient.recipeapp.presentation.model.IngredientItem
import javax.inject.Inject

class IngredientItemMapper @Inject constructor() : ItemMapper<Ingredient, IngredientItem> {

    override fun mapToPresentation(model: Ingredient) = IngredientItem(
        id = model.id,
        name = model.name,
        localizedName = model.localizedName,
        image = model.image
    )

    override fun mapToDomain(modelItem: IngredientItem) = Ingredient(
        id = modelItem.id,
        name = modelItem.name,
        localizedName = modelItem.localizedName,
        image = modelItem.image
    )

}