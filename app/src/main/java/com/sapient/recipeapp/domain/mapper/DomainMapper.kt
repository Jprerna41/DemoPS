package com.sapient.recipeapp.domain.mapper

interface DomainMapper<T, DomainModel> {
    fun mapToDomainModel(model: T): DomainModel
}