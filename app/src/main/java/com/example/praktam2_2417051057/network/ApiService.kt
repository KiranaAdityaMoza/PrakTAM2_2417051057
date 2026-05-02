package com.example.praktam2_2417051057.network

import Model.BMI
import retrofit2.http.GET

interface ApiService {

    @GET("bmi.json")
    suspend fun getBMI(): List<BMI>

}