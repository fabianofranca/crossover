package com.fabianofranca.crossover.ui.users

import androidx.lifecycle.viewModelScope
import com.fabianofranca.crossover.data.model.UserModel
import com.fabianofranca.crossover.domain.home.GetUsersUseCase
import com.fabianofranca.crossover.ui.commons.BaseViewModel
import com.fabianofranca.crossover.ui.commons.UiState
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface UsersUiState : UiState {
    object Loading : UsersUiState

    data class Success(val users: List<UserModel>) : UsersUiState

    data class Failure(val retry: () -> Unit, val message: String? = null) : UsersUiState
}

class UsersViewModel(private val useCase: GetUsersUseCase = GetUsersUseCase()) :
    BaseViewModel<UsersUiState>() {

    init {
        getUsers()
    }

    override fun initialUiState() = UsersUiState.Loading

    private fun getUsers() {
        mutableUiState.update { UsersUiState.Loading }

        viewModelScope.launch {
            try {
                useCase(1)
                    .collect { users ->
                        mutableUiState.update { UsersUiState.Success(users) }
                    }
            } catch (e: Throwable) {
                mutableUiState.update { UsersUiState.Failure(retry = ::getUsers) }
            }
        }
    }
}
