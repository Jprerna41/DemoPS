package com.sapient.recipeapp.ui.model.mapper

import com.sapient.recipeapp.domain.model.RecipeDomainModel
import com.sapient.recipeapp.ui.base.UiStateMapper
import com.sapient.recipeapp.ui.model.RecipeUiState
import javax.inject.Inject


class RecipeItemMapper @Inject constructor(private val instructionItemMapper: InstructionItemMapper) :
    UiStateMapper<RecipeDomainModel, RecipeUiState> {
    override fun mapToDomainModel(uiModel: RecipeUiState) = RecipeDomainModel(
        id = uiModel.id,
        title = uiModel.title,
        summary = uiModel.summary,
        imageUrl = uiModel.imageUrl,
        sourceName = uiModel.sourceName,
        analyzedInstructions = uiModel.analyzedInstructions?.map {
            instructionItemMapper.mapToDomainModel(
                it
            )
        },
        isFavourite = uiModel.isFavourite
    )

    override fun mapToUiModel(domainModel: RecipeDomainModel) = RecipeUiState(
        id = domainModel.id,
        title = domainModel.title,
        summary = domainModel.summary,
        imageUrl = domainModel.imageUrl,
        sourceName = domainModel.sourceName,
        analyzedInstructions = domainModel.analyzedInstructions?.map {
            instructionItemMapper.mapToUiModel(
                it
            )
        },
        isFavourite = domainModel.isFavourite
    )
}