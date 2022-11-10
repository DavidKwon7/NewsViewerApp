package com.example.newsviewerapp.ui.feature.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.entity.Article
import com.example.domain.usecase.CategoryNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    val useCase: CategoryNewsUseCase
) : ViewModel() {

    private var _categoryStateFlow = MutableStateFlow<CategoryState>(CategoryState.Empty)
    val categoryStateFlow: StateFlow<CategoryState> get() = _categoryStateFlow

    fun categoryArticle(country: String) =
        viewModelScope.launch {
            _categoryStateFlow.value = CategoryState.Loading
            useCase.invoke(country)
                .cachedIn(viewModelScope)
                .catch { e ->
                    _categoryStateFlow.value = CategoryState.Failed(e)
                }
                .collect() { categortData ->
                    _categoryStateFlow.value = CategoryState.Success(flowOf(categortData))
                }
        }
}

sealed class CategoryState() {
    object Empty : CategoryState()
    object Loading : CategoryState()
    class Success(var data: Flow<PagingData<Article>>) : CategoryState()
    class Failed(var message: Throwable) : CategoryState()
}

