package com.sapient.recipeapp.data.local.db.dao

import androidx.room.*
import com.sapient.recipeapp.data.model.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM ${RecipeEntity.TABLE_NAME}")
    fun getRecipeList(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM ${RecipeEntity.TABLE_NAME} WHERE ${RecipeEntity.COLUMN_ID} IN(:id)")
    fun getRecipe(id: Int): Flow<RecipeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: RecipeEntity) : Long

    @Delete
    fun deleteRecipe(recipe: RecipeEntity)
}