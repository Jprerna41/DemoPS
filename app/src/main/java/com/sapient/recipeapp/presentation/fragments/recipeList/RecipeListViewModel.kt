package com.sapient.recipeapp.presentation.fragments.recipeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapient.recipeapp.data.Result
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.domain.usecase.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
@Inject constructor(private val useCase: RecipeUseCase) : ViewModel() {

    private val recipesLiveDataPrivate = MutableLiveData<Result<List<RecipeItem>>>()
    val recipesLiveData: LiveData<Result<List<RecipeItem>>> get() = recipesLiveDataPrivate

    fun getRecipes() {
        viewModelScope.launch {
            recipesLiveDataPrivate.value = Result.Loading()
            useCase.getRecipes().collect { result ->
                recipesLiveDataPrivate.value = result
            }
        }
    }

    fun setFavorite(recipe: RecipeItem) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                if (!recipe.isFavourite) {
                    useCase.deleteFavorite(recipe)
                } else {
                    useCase.insertFavorite(recipe)
                }
            }
        }
    }

}