package com.sapient.recipeapp.presentation.fragments.recipeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapient.recipeapp.data.Result
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.domain.usecase.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class RecipeListViewModel
@Inject constructor(
    private val useCase: RecipeUseCase,
    private val ioDispatcher: CoroutineContext
) : ViewModel() {

    private val _recipesLiveDataPrivate = MutableLiveData<Result<List<RecipeItem>>>()
    val recipesLiveData: LiveData<Result<List<RecipeItem>>> get() = _recipesLiveDataPrivate

    fun getRecipes() {
        viewModelScope.launch {
            _recipesLiveDataPrivate.value = Result.Loading()
            useCase.getRecipes().collect { result ->
                _recipesLiveDataPrivate.value = result
            }
        }
    }

    fun setFavorite(recipe: RecipeItem) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                if (!recipe.isFavourite) {
                    useCase.deleteFavorite(recipe)
                } else {
                    useCase.insertFavorite(recipe)
                }
            }
        }
    }

}