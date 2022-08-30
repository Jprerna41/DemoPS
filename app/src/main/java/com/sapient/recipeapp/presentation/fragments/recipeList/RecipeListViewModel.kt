package com.sapient.recipeapp.presentation.fragments.recipeList

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapient.recipeapp.data.IRecipeRepository
import com.sapient.recipeapp.data.Result
import com.sapient.recipeapp.domain.model.RecipeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(private val dataRepository: IRecipeRepository) :
    ViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val recipesLiveDataPrivate = MutableLiveData<Result<List<RecipeItem>>>()
    val recipesLiveData: LiveData<Result<List<RecipeItem>>> get() = recipesLiveDataPrivate

    init {
        getRecipes()
    }

    fun getRecipes() {
        viewModelScope.launch {
            recipesLiveDataPrivate.value = Result.Loading()
            dataRepository.requestRecipes().collect { result ->
                recipesLiveDataPrivate.value =
                    Result.Success(result)
            }
        }
    }

}