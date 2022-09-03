package com.sapient.recipeapp.data.local

import com.sapient.recipeapp.data.local.entity.RecipeEntity
import com.sapient.recipeapp.data.local.room.RecipeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val recipeDao: RecipeDao,
) {

    fun getAllRecipeList(): Flow<List<RecipeEntity>> = recipeDao.getRecipeList()

    fun getFavorite(id: Int): Flow<RecipeEntity?> = recipeDao.getRecipe(id)

    fun insertFavorite(recipe: RecipeEntity) = recipeDao.insertRecipe(recipe)

    fun removeFavourite(recipe: RecipeEntity) = recipeDao.deleteRecipe(recipe)
}