package com.fabianofranca.crossover.data.networking.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PictureResponse(
    val large: String,
    val medium: String,
    val thumbnail: String
)
