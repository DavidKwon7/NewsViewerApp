package com.example.newsviewerapp.ui.feature.favorite_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Article
import com.example.domain.usecase.DeleteNewsUseCase
import com.example.newsviewerapp.ui.feature.search_detail.SearchDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteDetailViewModel @Inject constructor(
    private val deleteNewsUseCase: DeleteNewsUseCase
) : ViewModel() {

    private var _favoriteFlow = MutableStateFlow<SearchDetailState>(SearchDetailState.Empty)
    val favoriteFlow: StateFlow<SearchDetailState> get() = _favoriteFlow

    fun insertNews(article: Article) =
        viewModelScope.launch(Dispatchers.IO) {
            _favoriteFlow.value = SearchDetailState.Loading
            deleteNewsUseCase.invoke(article)
        }
}

sealed class FavoriteDetailState() {
    object Empty : FavoriteDetailState()
    object Loading : FavoriteDetailState()
    class Success(var data: List<Article>) : FavoriteDetailState()
    class Failed(var message: Throwable) : FavoriteDetailState()
}