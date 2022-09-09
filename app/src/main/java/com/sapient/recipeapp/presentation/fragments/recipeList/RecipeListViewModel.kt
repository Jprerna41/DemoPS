package com.sapient.recipeapp.presentation.fragments.recipeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapient.recipeapp.domain.usecase.FavouriteRecipesUseCase
import com.sapient.recipeapp.domain.usecase.GetRecipesUseCase
import com.sapient.recipeapp.presentation.model.RecipeItem
import com.sapient.recipeapp.presentation.model.mapper.RecipeItemMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class RecipeListViewModel
@Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getFavoriteRecipesUseCase: FavouriteRecipesUseCase,
    private val ioDispatcher: CoroutineContext,
    private val repoItemMapper: RecipeItemMapper
) : ViewModel() {

    private val _recipesLiveDataPrivate = MutableLiveData<List<RecipeItem>>()
    val recipesLiveData: LiveData<List<RecipeItem>> = _recipesLiveDataPrivate
    val loading = MutableLiveData<Boolean>().apply { postValue(false) }

    fun getRecipes() {
        viewModelScope.launch {
            loading.postValue(true)
            getRecipesUseCase.getRecipes().collect { result ->
                result.data?.map {
                    repoItemMapper.mapToPresentation(it)
                }.apply {
                    _recipesLiveDataPrivate.postValue(this)
                    loading.postValue(false)
                }
            }
        }
    }

    fun setFavorite(recipe: RecipeItem) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                if (!recipe.isFavourite) {
                    getFavoriteRecipesUseCase.deleteFavorite(repoItemMapper.mapToDomain(recipe))
                } else {
                    getFavoriteRecipesUseCase.insertFavorite(repoItemMapper.mapToDomain(recipe))
                }
            }
        }
    }

}