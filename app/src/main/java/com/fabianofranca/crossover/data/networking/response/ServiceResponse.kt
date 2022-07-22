package com.fabianofranca.crossover.data.networking.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServiceResponse(
    val results: List<UserResponse>,
    val info: PaginationResponse
)
