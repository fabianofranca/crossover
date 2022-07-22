package com.fabianofranca.crossover.data

import com.fabianofranca.crossover.data.model.UserModel
import com.fabianofranca.crossover.data.networking.RandomUserDataSource
import com.fabianofranca.crossover.data.networking.RandomUserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UsersRepository(private val service: RandomUserService = RandomUserDataSource()) {

    fun get(page: Int): Flow<List<UserModel>> = flow {
        emit(
            service.users(page).results.map {
                UserModel(
                    fullName = "${it.name.first} ${it.name.last}",
                    email = it.email,
                    phone = it.cell,
                    picture = it.picture.large
                )
            }
        )
    }
}
