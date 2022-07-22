package com.fabianofranca.crossover.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.fabianofranca.crossover.R
import com.fabianofranca.crossover.data.model.UserModel
import com.fabianofranca.crossover.databinding.FragmentUsersBinding
import com.fabianofranca.crossover.databinding.ItemUserBinding
import com.fabianofranca.crossover.ui.commons.ViewBindingFragment
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.viewbinding.BindableItem

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
        adapter.addAll(uiState.users.map { UserItem(it) })
    }

    override fun setupViews(binding: FragmentUsersBinding) {
        binding.recyclerView.adapter = adapter
    }
    private class UserItem(private val user: UserModel) : BindableItem<ItemUserBinding>() {

        override fun bind(viewBinding: ItemUserBinding, position: Int) {
            with(viewBinding) {
                userName.text = user.fullName
                userEmail.text = user.email
                userPhone.text = user.phone

                Glide.with(root)
                    .load(user.picture)
                    .placeholder(R.drawable.placeholder)
                    .into(userImage)
            }
        }

        override fun getLayout() = R.layout.item_user

        override fun initializeViewBinding(view: View) = ItemUserBinding.bind(view)
    }
}
