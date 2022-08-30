package com.sapient.recipeapp.di

import com.sapient.recipeapp.data.IRecipeRepository
import com.sapient.recipeapp.data.RecipeRepositoryImpl
import com.sapient.recipeapp.data.remote.network.RecipeServices
import com.sapient.recipeapp.data.remote.network.model.RecipeDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Tells Dagger this is a Dagger module
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeServices,
        recipeMapper: RecipeDtoMapper,
    ): IRecipeRepository {
        return RecipeRepositoryImpl(
            recipeService = recipeService,
            mapper = recipeMapper
        )
    }
}
