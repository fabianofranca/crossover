package com.fabianofranca.crossover.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fabianofranca.crossover.databinding.FragmentHomeBinding
import com.fabianofranca.crossover.ui.commons.ViewBindingFragment
import com.fabianofranca.crossover.ui.commons.toggleVisibility
import com.google.android.material.snackbar.Snackbar

class HomeFragment : ViewBindingFragment<HomeUiState, HomeViewModel, FragmentHomeBinding>() {

    override val viewModel by viewModels<HomeViewModel>()

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentHomeBinding.inflate(inflater, container, attachToParent)

    override suspend fun FragmentHomeBinding.newUiState(uiState: HomeUiState) {

        txtMessage.text = uiState.message

        txtMessage.toggleVisibility(uiState.hasMessage, View.INVISIBLE)

        btnSend.setOnClickListener {
            uiState.send(edName.text.toString())
        }

        uiState.error?.let {
            Snackbar.make(root, it, Snackbar.LENGTH_SHORT).show()
            uiState.clearError()
        }
    }
}
