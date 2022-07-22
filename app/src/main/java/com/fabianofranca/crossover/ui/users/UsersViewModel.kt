package com.fabianofranca.crossover.ui.users

import androidx.lifecycle.viewModelScope
import com.fabianofranca.crossover.data.model.UserModel
import com.fabianofranca.crossover.domain.home.GetUsersUseCase
import com.fabianofranca.crossover.ui.commons.BaseViewModel
import com.fabianofranca.crossover.ui.commons.UiState
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UsersUiState(
    val users: List<UserModel> = emptyList(),
    override val error: String? = null,
    override val clearError: () -> Unit
) : UiState

class UsersViewModel(private val useCase: GetUsersUseCase = GetUsersUseCase()) :
    BaseViewModel<UsersUiState>() {

    init {
        getUsers()
    }

    override fun initialUiState() = UsersUiState(
        clearError = ::clearError
    )

    private fun getUsers() {
        viewModelScope.launch {
            try {
                useCase(1)
                    .collect { users ->
                        mutableUiState.update {
                            it.copy(users = users)
                        }
                    }
            } catch (e: Throwable) {
                mutableUiState.update { it.copy(error = e.message) }
            }
        }
    }

    override fun clearingError(uiState: UsersUiState) = uiState.copy(
        error = null
    )
}
