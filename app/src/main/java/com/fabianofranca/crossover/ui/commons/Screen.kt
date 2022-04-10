package com.fabianofranca.crossover.ui.commons

interface Screen<
    TUiState : UiState,
    TViewModel : BaseViewModel<TUiState>> {
    val viewModel: TViewModel
}
