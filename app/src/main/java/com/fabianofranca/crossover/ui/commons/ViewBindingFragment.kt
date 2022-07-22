package com.fabianofranca.crossover.ui.commons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

abstract class ViewBindingFragment<
    TUiState : UiState,
    TViewModel : BaseViewModel<TUiState>,
    TViewBinding : ViewBinding> : Fragment(), Screen<TUiState, TViewModel> {

    private var binding: TViewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, container, false) as TViewBinding
        val view = binding!!.root
        setupUiState()
        return view
    }

    abstract fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): ViewBinding

    protected open fun setupUiState() {
        binding?.let { setupViews(it) }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collect {
                    binding?.newUiState(it)
                }
        }
    }

    protected open fun setupViews(binding: TViewBinding) {
        // Set up yours views if need
    }

    abstract suspend fun TViewBinding.newUiState(uiState: TUiState)

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
