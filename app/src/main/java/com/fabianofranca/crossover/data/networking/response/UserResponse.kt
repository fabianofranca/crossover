package com.fabianofranca.crossover.data.networking.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    val name: UserNameResponse,
    val email: String,
    val cell: String,
    val picture: PictureResponse
)
