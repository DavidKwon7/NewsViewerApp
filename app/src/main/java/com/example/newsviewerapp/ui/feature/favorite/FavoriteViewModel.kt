package com.example.newsviewerapp.ui.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Article
import com.example.domain.usecase.DeleteNewsUseCase
import com.example.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val deleteNewsUseCase: DeleteNewsUseCase
) : ViewModel() {

    private var _favoriteStateFlow = MutableStateFlow<FavoriteState>(FavoriteState.Empty)
    val favoriteStateFlow: StateFlow<FavoriteState> get() = _favoriteStateFlow

    fun getNews() =
        viewModelScope.launch {
            _favoriteStateFlow.value = FavoriteState.Loading
            getNewsUseCase.invoke()
                .catch { e ->
                    _favoriteStateFlow.value = FavoriteState.Failed(e)
                }.collect { favoriteData ->
                    _favoriteStateFlow.value = FavoriteState.Success(favoriteData)
                }
        }
}

sealed class FavoriteState() {
    object Empty : FavoriteState()
    object Loading : FavoriteState()
    class Success(var data: List<Article>) : FavoriteState()
    class Failed(var message: Throwable) : FavoriteState()
}