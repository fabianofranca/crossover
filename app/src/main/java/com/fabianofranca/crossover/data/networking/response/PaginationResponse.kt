package com.fabianofranca.crossover.data.networking.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationResponse(
    val seed: String,
    val results: Int,
    val page: Int
)
