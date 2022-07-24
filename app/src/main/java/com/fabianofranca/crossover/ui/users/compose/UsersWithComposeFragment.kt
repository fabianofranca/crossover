package com.fabianofranca.crossover.ui.users.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.fabianofranca.crossover.R
import com.fabianofranca.crossover.ui.Templates
import com.fabianofranca.crossover.ui.commons.ComposeFragment
import com.fabianofranca.crossover.ui.users.UsersUiState
import com.fabianofranca.crossover.ui.users.UsersViewModel

class UsersWithComposeFragment : ComposeFragment<UsersUiState, UsersViewModel>() {

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
                UserCard(user = user)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun LoadingPreview() {
        AppTheme {
            Compose(UsersUiState.Loading)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun SuccessPreview() {
        AppTheme {
            Compose(UsersUiState.Success(users = Templates.users))
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun FailurePreview() {
        AppTheme {
            Compose(
                UsersUiState.Failure(
                    retry = {},
                    message = Templates.lorem
                )
            )
        }
    }
}

@Composable
fun UsersLoading() {
    CircularProgressIndicator(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    )
}

@Composable
fun UsersFailure(uiState: UsersUiState.Failure) {
    Column(
        Modifier
            .padding(horizontal = 16.dp)
            .wrapContentHeight()
    ) {
        val alignModifier = Modifier
            .align(Alignment.CenterHorizontally)
        Text(
            text = uiState.message ?: stringResource(R.string.error_message),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .composed { alignModifier }
        )
        Button(
            modifier = alignModifier,
            onClick = uiState.retry
        ) {
            Text(text = stringResource(R.string.Retry))
        }
    }
}

