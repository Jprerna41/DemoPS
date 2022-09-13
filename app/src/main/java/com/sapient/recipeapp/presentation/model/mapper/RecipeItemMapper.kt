package com.sapient.recipeapp.presentation.model.mapper

import com.sapient.recipeapp.domain.model.RecipeDomainModel
import com.sapient.recipeapp.presentation.base.ItemMapper
import com.sapient.recipeapp.presentation.model.RecipeItem
import javax.inject.Inject


class RecipeItemMapper @Inject constructor(private val instructionItemMapper: InstructionItemMapper) :
    ItemMapper<RecipeDomainModel, RecipeItem> {
    override fun mapToPresentation(model: RecipeDomainModel) = RecipeItem(
        id = model.id,
        title = model.title,
        summary = model.summary,
        imageUrl = model.imageUrl,
        imageType = model.imageType,
        sourceName = model.sourceName,
        dishTypes = model.dishTypes,
        analyzedInstructions = model.analyzedInstructions?.map {
            instructionItemMapper.mapToPresentation(
                it
            )
        },
        isFavourite = model.isFavourite
    )

    override fun mapToDomain(modelItem: RecipeItem) = RecipeDomainModel(
        id = modelItem.id,
        title = modelItem.title,
        summary = modelItem.summary,
        imageUrl = modelItem.imageUrl,
        imageType = modelItem.imageType,
        sourceName = modelItem.sourceName,
        dishTypes = modelItem.dishTypes,
        analyzedInstructions = modelItem.analyzedInstructions?.map {
            instructionItemMapper.mapToDomain(
                it
            )
        },
        isFavourite = modelItem.isFavourite
    )
}