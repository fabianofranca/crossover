package com.fabianofranca.crossover.ui.users.viewbinding

import android.view.View
import com.bumptech.glide.Glide
import com.fabianofranca.crossover.R
import com.fabianofranca.crossover.data.model.UserModel
import com.fabianofranca.crossover.databinding.ItemUserBinding
import com.fabianofranca.crossover.ui.commons.ComposableBindableItem

class UserItem(private val user: UserModel) : ComposableBindableItem<ItemUserBinding>() {

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

    public override fun initializeViewBinding(view: View) = ItemUserBinding.bind(view)
}
