package com.sapient.recipeapp.di

import android.content.Context
import com.sapient.recipeapp.data.local.db.RecipeDatabase
import com.sapient.recipeapp.data.local.db.dao.RecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): RecipeDatabase {
        return RecipeDatabase.getInstance(context)
    }

    @Provides
    fun provideRecipeDao(appDatabase: RecipeDatabase): RecipeDao {
        return appDatabase.recipeDao()
    }
}
