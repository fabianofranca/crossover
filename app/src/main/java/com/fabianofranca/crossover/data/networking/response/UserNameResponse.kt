package com.fabianofranca.crossover.data.networking.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserNameResponse(
    val first: String,
    val last: String
)
