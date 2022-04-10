package com.fabianofranca.crossover.ui.commons

interface UiState {
    val error: String?
    val clearError: () -> Unit
}
