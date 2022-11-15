package com.example.newsviewerapp.ui.feature.search_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Article
import com.example.domain.usecase.InsertNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    private val insertNewsUseCase: InsertNewsUseCase
) : ViewModel() {

    private var _favoriteFlow = MutableStateFlow<SearchDetailState>(SearchDetailState.Empty)
    val favoriteFlow: StateFlow<SearchDetailState> get() = _favoriteFlow

    fun insertNews(article: Article) =
        viewModelScope.launch(Dispatchers.IO) {
            _favoriteFlow.value = SearchDetailState.Loading
            insertNewsUseCase.invoke(article)
        }
}

sealed class SearchDetailState() {
    object Empty : SearchDetailState()
    object Loading : SearchDetailState()
    class Success(var data: List<Article>) : SearchDetailState()
    class Failed(var message: Throwable) : SearchDetailState()
}