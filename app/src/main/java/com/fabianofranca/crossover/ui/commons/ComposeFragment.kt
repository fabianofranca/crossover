package com.fabianofranca.crossover.ui.commons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.fabianofranca.crossover.ui.theme.CrossoverTheme

abstract class ComposeFragment<TUiState : UiState, TViewModel : BaseViewModel<TUiState>> :
    Fragment(), Screen<TUiState, TViewModel> {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnLifecycleDestroyed(
                viewLifecycleOwner
            )
        )

        setContent {
            val uiState by viewModel.uiState.collectAsState()

            AppTheme { this@ComposeFragment.Compose(uiState) }
        }
    }

    @Composable
    protected fun AppTheme(content: @Composable () -> Unit) {
        CrossoverTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background,
                content = content
            )
        }
    }

    @Composable
    abstract fun Compose(uiState: TUiState)
}
