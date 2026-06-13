package com.example.praktam2_2417051057.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL =
        "https://gist.githubusercontent.com/KiranaAdityaMoza/7c6f84e902473c4c3aba204a4af2eba3/raw/3690aefd697017437ea138bdc30160e62086448c/"

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
