package com.fabianofranca.crossover.data.networking

import com.fabianofranca.crossover.data.networking.response.ServiceResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RandomUserDataSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    operator fun invoke(): RandomUserService = retrofit.create(RandomUserService::class.java)
}

interface RandomUserService {
    @GET("api?inc=name,picture,email,cell&nat=br")
    suspend fun users(
        @Query("page") page: Int,
        @Query("results") results: Int = RESULTS,
        @Query("seed") seed: String = SEED
    ): ServiceResponse

    private companion object {
        const val RESULTS = 20
        const val SEED = "crossover"
    }
}
