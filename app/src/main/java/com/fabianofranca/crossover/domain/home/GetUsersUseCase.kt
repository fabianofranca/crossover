package com.fabianofranca.crossover.domain.home

import com.fabianofranca.crossover.data.UsersRepository
import com.fabianofranca.crossover.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetUsersUseCase(private val repository: UsersRepository = UsersRepository()) {

    operator fun invoke(page: Int): Flow<List<UserModel>> =
        repository.get(page).flowOn(Dispatchers.IO)
}
