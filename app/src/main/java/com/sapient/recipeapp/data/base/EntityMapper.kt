package com.sapient.recipeapp.data.base

interface EntityMapper<T, EntityModel> {
    fun mapToDomainModel(uiModel: EntityModel): T
    fun mapToDataModel(domainModel: T): EntityModel
}