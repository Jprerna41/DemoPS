package com.sapient.recipeapp.di

import com.sapient.recipeapp.data.RecipeRepositoryImpl
import com.sapient.recipeapp.data.local.LocalDataSource
import com.sapient.recipeapp.data.remote.RemoteDataSource
import com.sapient.recipeapp.domain.repository.ICoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
    ): ICoreRepository {
        return RecipeRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
        )
    }
}
