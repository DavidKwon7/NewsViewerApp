package com.example.newsviewerapp.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
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

    private var _searchStateFlow: MutableStateFlow<PagingData<SearchState>> =
        MutableStateFlow(PagingData.empty())
    val searchStateFlow: StateFlow<PagingData<SearchState>> get() = _searchStateFlow.asStateFlow()

    private var _stateFlow = MutableStateFlow<SearchState>(SearchState.Empty)
    val stateFlow: StateFlow<SearchState> get() = _stateFlow

    fun searchNews(q: String) =
        useCase.invoke(q).cachedIn(viewModelScope)

    fun searchArticle(query: String) =
        viewModelScope.launch {
            _stateFlow.value = SearchState.Loading
            useCase.invoke(query)
                .cachedIn(viewModelScope)
                .catch { e ->
                    _stateFlow.value = SearchState.Failed(e)
                }.collect {searchData ->
                    // 이렇게 하면 되나..?
                    _stateFlow.value = SearchState.Success(flowOf(searchData))

                }
        }
}

sealed class SearchState() {
    object Empty: SearchState()
    object Loading: SearchState()
    class Success(var data: Flow<PagingData<Article>>) : SearchState()
    class Failed(var message: Throwable) : SearchState()
}