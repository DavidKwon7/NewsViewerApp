package com.example.newsviewerapp.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.domain.entity.Article
import com.example.domain.repository.SearchNewsRepository
import com.example.domain.usecase.SearchNewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val useCase: SearchNewUseCase
) : ViewModel() {

    private var _searchStateFlow = MutableStateFlow<SearchState>(SearchState.Empty)
    val searchStateFlow: StateFlow<SearchState> get() = _searchStateFlow

    fun searchNews(query: String) =
        viewModelScope.launch {
            _searchStateFlow.value = SearchState.Loading
            useCase.invoke(query)
                .catch { e ->
                    _searchStateFlow.value = SearchState.Failed(e)
                }.collect {searchData ->
                    // 이렇게 하면 되나..?
                    _searchStateFlow.value = SearchState.Success(flowOf(searchData))
                }
        }
}

sealed class SearchState() {
    object Empty: SearchState()
    object Loading: SearchState()
    class Success(var data: Flow<PagingData<Article>>) : SearchState()
    class Failed(var message: Throwable) : SearchState()
}