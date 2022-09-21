package com.sapient.recipeapp.ui.fragments.recipeList

import androidx.annotation.VisibleForTesting
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
import kotlinx.coroutines.withContext
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

    private val _recipesData = MutableLiveData<List<RecipeUiState>?>()
    val recipesData: LiveData<List<RecipeUiState>?> = _recipesData

    private val _pbLoading = MutableLiveData<Boolean>()
    val pbLoading: LiveData<Boolean> = _pbLoading

    fun getRecipes() {
        viewModelScope.launch {
            _pbLoading.postValue(true)
            getRecipesUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> _pbLoading.postValue(true)
                    is Resource.Success -> {
                        result.data?.map { recipe -> repoItemMapper.mapToUiModel(recipe) }
                            .apply {
                                _recipesData.postValue(this)
                                _pbLoading.postValue(false)
                            }
                    }
                    is Resource.Error -> {
                        _recipesData.postValue(null)
                        _pbLoading.postValue(false)
                    }
                }
            }
        }
    }

    fun addFavourite(recipe: RecipeUiState) {
        viewModelScope.launch {
            withContext(dispatcher) {
                insertFavRecipeUseCase(repoItemMapper.mapToDomainModel(recipe))
            }
        }
    }

    fun removeFavourite(recipe: RecipeUiState) {
        viewModelScope.launch {
            withContext(dispatcher) {
                removeFavRecipeUseCase(repoItemMapper.mapToDomainModel(recipe))
            }
        }
    }

    @VisibleForTesting
    fun setRecipeLiveData(list : List<RecipeUiState>){
        _recipesData.postValue(list)
    }
}

