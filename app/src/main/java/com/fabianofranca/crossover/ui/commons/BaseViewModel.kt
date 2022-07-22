package com.fabianofranca.crossover.ui.commons

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<TUiState : UiState> : ViewModel() {

    abstract fun initialUiState(): TUiState

    protected val mutableUiState: MutableStateFlow<TUiState> by lazy {
        MutableStateFlow(initialUiState())
    }

    val uiState: StateFlow<TUiState> = mutableUiState.asStateFlow()

    fun clearError() {
        mutableUiState.update { clearingError(it) }
    }

    protected abstract fun clearingError(uiState: TUiState): TUiState
}
