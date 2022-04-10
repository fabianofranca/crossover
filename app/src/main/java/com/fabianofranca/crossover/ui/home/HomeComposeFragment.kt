package com.fabianofranca.crossover.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import com.fabianofranca.crossover.R
import com.fabianofranca.crossover.ui.commons.ComposeFragment
import kotlinx.coroutines.launch

class HomeComposeFragment : ComposeFragment<HomeUiState, HomeViewModel>() {

    override val viewModel by viewModels<HomeViewModel>()

    @Composable
    override fun Compose(uiState: HomeUiState) {
        val (text, setText) = rememberSaveable { mutableStateOf("") }
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        with(uiState) {
            Column {
                NameInputText(text = text, onTextChange = setText)
                SendButton(onClick = {
                    send(text)
                })
                MessageText(message = message)
            }
            error?.let {
                scope.launch { snackbarHostState.showSnackbar(it) }
                clearError()
            }
        }

        SnackbarHost(hostState = snackbarHostState)
    }

    @Composable
    fun NameInputText(
        text: String,
        onTextChange: (String) -> Unit,
        modifier: Modifier = Modifier
    ) {
        TextField(
            value = text,
            onValueChange = onTextChange,
            maxLines = 1,
            modifier = modifier,
            label = {
                Text(stringResource(id = R.string.input_your_name))
            }
        )
    }

    @Composable
    fun SendButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        TextButton(
            onClick = onClick,
            modifier = modifier
        ) {
            Text(stringResource(id = R.string.send))
        }
    }

    @Composable
    fun MessageText(message: String?) {
        if (!message.isNullOrBlank()) Text(text = message)
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        AppTheme {
            Compose(
                HomeUiState(
                    message = "Hello world!",
                    send = {},
                    clearError = {}
                )
            )
        }
    }
}
