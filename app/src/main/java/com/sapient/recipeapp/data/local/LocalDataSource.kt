package com.sapient.recipeapp.data.local

import com.sapient.recipeapp.data.local.db.dao.RecipeDao
import com.sapient.recipeapp.data.model.RecipeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val recipeDao: RecipeDao,
) {
    fun getFavorite(id: Int): Flow<RecipeEntity?> = recipeDao.getRecipe(id)

    fun insertFavorite(recipe: RecipeEntity) = recipeDao.insertRecipe(recipe)

    fun removeFavourite(recipe: RecipeEntity) = recipeDao.deleteRecipe(recipe)
}