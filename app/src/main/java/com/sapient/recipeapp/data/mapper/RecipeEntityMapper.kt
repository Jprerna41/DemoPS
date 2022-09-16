package com.sapient.recipeapp.data.mapper

import com.sapient.recipeapp.data.base.EntityMapper
import com.sapient.recipeapp.data.model.RecipeEntity
import com.sapient.recipeapp.domain.model.RecipeDomainModel
import javax.inject.Inject

class RecipeEntityMapper @Inject constructor(private val instructionEntityMapper: InstructionEntityMapper) :
    EntityMapper<RecipeDomainModel, RecipeEntity> {
    override fun mapToDomainModel(uiModel: RecipeEntity) = RecipeDomainModel(
        id = uiModel.id,
        title = uiModel.title,
        summary = uiModel.summary,
        imageUrl = uiModel.image,
        sourceName = uiModel.sourceName,
        analyzedInstructions = uiModel.analyzedInstructions?.map {
            instructionEntityMapper.mapToDomainModel(it)
        },
        isFavourite = uiModel.isFavourite
    )

    override fun mapToDataModel(domainModel: RecipeDomainModel) = RecipeEntity(
        id = domainModel.id,
        title = domainModel.title,
        summary = domainModel.summary,
        image = domainModel.imageUrl,
        imageType = "",
        sourceName = domainModel.sourceName,
        dishTypes = listOf(),
        analyzedInstructions = domainModel.analyzedInstructions?.map {
            instructionEntityMapper.mapToDataModel(it)
        },
        isFavourite = domainModel.isFavourite
    )

}