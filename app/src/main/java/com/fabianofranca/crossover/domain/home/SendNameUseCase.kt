package com.fabianofranca.crossover.domain.home

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SendNameUseCase() {
    suspend operator fun invoke(name: String): Result<String> = runCatching {
        withContext(Dispatchers.IO) {
            if (name.isBlank()) throw Throwable("Name cannot be empty")

            "Hello $name!"
        }
    }
}
