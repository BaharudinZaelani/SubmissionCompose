package com.bahardev.submissioncompose.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahardev.submissioncompose.data.AnimeRepository
import com.bahardev.submissioncompose.model.Anime
import com.bahardev.submissioncompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: AnimeRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Anime>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Anime>> get() = _uiState

    fun getHeroById(heroId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getHeroById(heroId))
        }
    }
}