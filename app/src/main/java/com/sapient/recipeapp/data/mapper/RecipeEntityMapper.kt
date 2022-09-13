package com.sapient.recipeapp.data.mapper

import com.sapient.recipeapp.data.base.EntityMapper
import com.sapient.recipeapp.data.model.RecipeEntity
import com.sapient.recipeapp.domain.model.RecipeDomainModel
import javax.inject.Inject


class RecipeEntityMapper @Inject constructor(private val instructionEntityMapper: InstructionEntityMapper) :
    EntityMapper<RecipeDomainModel, RecipeEntity> {

    override fun mapToDomain(entity: RecipeEntity): RecipeDomainModel = RecipeDomainModel(
        id = entity.id,
        title = entity.title,
        summary = entity.summary,
        imageUrl = entity.image,
        imageType = entity.imageType,
        sourceName = entity.sourceName,
        dishTypes = entity.dishTypes,
        analyzedInstructions = entity.analyzedInstructions?.map {
            instructionEntityMapper.mapToDomain(it)
        },
        isFavourite = entity.isFavourite
    )

    override fun mapToEntity(model: RecipeDomainModel): RecipeEntity = RecipeEntity(
        id = model.id,
        title = model.title,
        summary = model.summary,
        image = model.imageUrl,
        imageType = model.imageType,
        sourceName = model.sourceName,
        dishTypes = model.dishTypes,
        analyzedInstructions = model.analyzedInstructions?.map {
            instructionEntityMapper.mapToEntity(it)
        },
        isFavourite = model.isFavourite
    )
}