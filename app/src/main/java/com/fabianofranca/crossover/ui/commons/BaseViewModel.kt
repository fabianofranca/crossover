package com.fabianofranca.crossover.ui.commons

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface UiState

abstract class BaseViewModel<TUiState : UiState> : ViewModel() {

    abstract fun initialUiState(): TUiState

    protected val mutableUiState: MutableStateFlow<TUiState> by lazy {
        MutableStateFlow(initialUiState())
    }

    val uiState: StateFlow<TUiState> = mutableUiState.asStateFlow()
}
