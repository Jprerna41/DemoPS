package com.sapient.recipeapp.presentation.fragments.recipeList

import androidx.lifecycle.*
import com.sapient.recipeapp.data.Result
import com.sapient.recipeapp.domain.model.RecipeItem
import com.sapient.recipeapp.domain.usecase.CoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
@Inject constructor(private val useCase: CoreUseCase) : ViewModel() {

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
        CoroutineScope(Dispatchers.IO).launch {
            if (!recipe.isFavourite) {
                useCase.deleteFavorite(recipe)
            } else {
                useCase.insertFavorite(recipe)
            }
        }
    }

}