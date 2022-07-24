package com.fabianofranca.crossover.ui.users.compose_groupie

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.viewModels
import com.fabianofranca.crossover.data.model.UserModel
import com.fabianofranca.crossover.ui.commons.BindableItemToCompose
import com.fabianofranca.crossover.ui.commons.ComposeFragment
import com.fabianofranca.crossover.ui.users.UsersUiState
import com.fabianofranca.crossover.ui.users.UsersViewModel
import com.fabianofranca.crossover.ui.users.compose.UsersFailure
import com.fabianofranca.crossover.ui.users.compose.UsersLoading
import com.fabianofranca.crossover.ui.users.viewbinding.UserItem

class UsersWithComposeAndGroupieFragment : ComposeFragment<UsersUiState, UsersViewModel>() {

    override val viewModel by viewModels<UsersViewModel>()

    @Composable
    override fun Compose(uiState: UsersUiState) {
        when (uiState) {
            is UsersUiState.Loading -> UsersLoading()
            is UsersUiState.Success -> Success(uiState)
            is UsersUiState.Failure -> UsersFailure(uiState)
        }
    }

    @Composable
    fun Success(uiState: UsersUiState.Success) {
        LazyColumn {
            items(uiState.users) { user ->
                UserCardView(user = user)
            }
        }
    }

    @Composable
    fun UserCardView(user: UserModel, modifier: Modifier = Modifier) {
        UserItem(user).BindableItemToCompose()
    }
}
