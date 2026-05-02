package com.example.praktam2_2417051057.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL =
        "https://gist.githubusercontent.com/KiranaAdityaMoza/7c6f84e902473c4c3aba204a4af2eba3/raw/d57927e33c7a2f4859cde86db90c4fae95daed66/"

    val instance: ApiService by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(ApiService::class.java)

    }
}