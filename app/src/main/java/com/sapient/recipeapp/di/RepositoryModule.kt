package com.sapient.recipeapp.di

import androidx.annotation.NonNull
import com.sapient.recipeapp.data.local.LocalDataSource
import com.sapient.recipeapp.data.mapper.RecipeEntityMapper
import com.sapient.recipeapp.data.remote.RemoteDataSource
import com.sapient.recipeapp.data.repository.RecipeRepositoryImpl
import com.sapient.recipeapp.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @NonNull
    @Binds
    abstract fun provideRecipeRepository(
        recipeRepositoryImpl: RecipeRepositoryImpl
    ): RecipeRepository
}
