package com.bahardev.submissioncompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahardev.submissioncompose.data.AnimeRepository
import com.bahardev.submissioncompose.model.Anime
import com.bahardev.submissioncompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: AnimeRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Anime>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Anime>>> get() = _uiState

    fun getAllHero() {
        viewModelScope.launch {
            repository.getAllHero().catch {
                _uiState.value = UiState.Error(it.message.toString())
            }.collect { heroes ->
                _uiState.value = UiState.Success(heroes)
            }
        }
    }
}