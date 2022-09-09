package com.sapient.recipeapp.di

import com.sapient.recipeapp.data.RecipeRepositoryImpl
import com.sapient.recipeapp.data.local.LocalDataSource
import com.sapient.recipeapp.data.mapper.RecipeEntityMapper
import com.sapient.recipeapp.data.remote.RemoteDataSource
import com.sapient.recipeapp.domain.repository.RecipeRepository
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
        mapper: RecipeEntityMapper
    ): RecipeRepository {
        return RecipeRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            mapper = mapper
        )
    }

}
