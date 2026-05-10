package com.example.praktam2_2417051057.data.api

import com.example.praktam2_2417051057.data.model.BMI
import retrofit2.http.GET

interface ApiService {

    @GET("bmi.json")
    suspend fun getBMI(): List<BMI>

}