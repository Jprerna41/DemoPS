package com.sapient.recipeapp.ui.fragments.recipeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapient.recipeapp.domain.usecase.GetRecipesUseCase
import com.sapient.recipeapp.domain.usecase.InsertFavRecipeUseCase
import com.sapient.recipeapp.domain.usecase.RemoveFavRecipeUseCase
import com.sapient.recipeapp.domain.utils.Resource
import com.sapient.recipeapp.ui.model.RecipeUiState
import com.sapient.recipeapp.ui.model.mapper.RecipeItemMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class RecipeListViewModel
@Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val insertFavRecipeUseCase: InsertFavRecipeUseCase,
    private val removeFavRecipeUseCase: RemoveFavRecipeUseCase,
    private val dispatcher: CoroutineContext,
    private val repoItemMapper: RecipeItemMapper
) : ViewModel() {

    private val _recipesData = MutableLiveData<Resource<List<RecipeUiState>?>>()
    val recipesData: LiveData<Resource<List<RecipeUiState>?>> = _recipesData

    fun getRecipes() {
        viewModelScope.launch {
            _recipesData.postValue(Resource.Loading())
            getRecipesUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> _recipesData.postValue(Resource.Loading())
                    is Resource.Success -> {
                        result.data?.let {
                            val uiRecipeState =
                                it.map { recipe -> repoItemMapper.mapToUiModel(recipe) }
                            _recipesData.postValue(Resource.Success(uiRecipeState))
                        }
                    }
                    is Resource.Error -> {
                        _recipesData.postValue(Resource.Error(result.errorMessage))
                    }
                }
            }
        }
    }

    fun addFavourite(recipe: RecipeUiState) {
        viewModelScope.launch(dispatcher) {
            insertFavRecipeUseCase(repoItemMapper.mapToDomainModel(recipe))
        }
    }

    fun removeFavourite(recipe: RecipeUiState) {
        viewModelScope.launch(dispatcher) {
            removeFavRecipeUseCase(repoItemMapper.mapToDomainModel(recipe))
        }
    }
}

