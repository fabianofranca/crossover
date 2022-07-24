package com.fabianofranca.crossover.ui.users.viewbinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fabianofranca.crossover.R
import com.fabianofranca.crossover.databinding.FragmentUsersBinding
import com.fabianofranca.crossover.ui.commons.ViewBindingFragment
import com.fabianofranca.crossover.ui.users.UsersUiState
import com.fabianofranca.crossover.ui.users.UsersViewModel
import com.xwray.groupie.GroupieAdapter

class UsersWithViewBindingFragment :
    ViewBindingFragment<UsersUiState, UsersViewModel, FragmentUsersBinding>() {

    override val viewModel by viewModels<UsersViewModel>()

    private val adapter: GroupieAdapter = GroupieAdapter()

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentUsersBinding.inflate(inflater, container, attachToParent)

    override suspend fun FragmentUsersBinding.newUiState(uiState: UsersUiState) {
        when (uiState) {
            is UsersUiState.Loading -> loading()
            is UsersUiState.Success -> success(uiState)
            is UsersUiState.Failure -> failure(uiState)
        }
    }

    private fun loading() {
        binding.progressIndicator.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.groupRetry.visibility = View.GONE
    }

    private fun success(uiState: UsersUiState.Success) {
        binding.progressIndicator.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        binding.groupRetry.visibility = View.GONE
        adapter.addAll(uiState.users.map { UserItem(it) })
    }

    private fun failure(uiState: UsersUiState.Failure) {
        binding.progressIndicator.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.groupRetry.visibility = View.VISIBLE

        binding.txtMessage.text = uiState.message ?: getString(R.string.error_message)
        binding.btnRetry.setOnClickListener { uiState.retry() }
    }

    override fun bindingCreated() {
        binding.recyclerView.adapter = adapter
    }
}
