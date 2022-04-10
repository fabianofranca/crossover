package com.fabianofranca.crossover.ui.home

import androidx.lifecycle.viewModelScope
import com.fabianofranca.crossover.domain.home.SendNameUseCase
import com.fabianofranca.crossover.ui.commons.BaseViewModel
import com.fabianofranca.crossover.ui.commons.UiState
import kotlinx.coroutines.launch

data class HomeUiState(
    val message: String? = null,
    val send: (String) -> Unit,
    override val error: String? = null,
    override val clearError: () -> Unit
) : UiState

val HomeUiState.hasMessage: Boolean get() = !message.isNullOrBlank()

class HomeViewModel(private val useCase: SendNameUseCase = SendNameUseCase()) :
    BaseViewModel<HomeUiState>() {

    override fun initialUiState() = HomeUiState(
        send = ::send,
        clearError = ::clearError
    )

    private fun send(name: String) {
        viewModelScope.launch {
            useCase(name)
                .updateOnSuccess { message -> copy(message = message) }
                .updateOnFailure { error -> copy(error = error.message) }
        }
    }

    override fun clearingError(uiState: HomeUiState) = uiState.copy(
        error = null
    )
}
