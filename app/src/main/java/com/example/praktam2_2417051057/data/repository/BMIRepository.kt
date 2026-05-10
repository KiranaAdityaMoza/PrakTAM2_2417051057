package com.example.praktam2_2417051057.data.repository

import com.example.praktam2_2417051057.data.api.RetrofitClient
import com.example.praktam2_2417051057.data.model.BMI

class BMIRepository {
    suspend fun getBMI(): List<BMI> {
        return try {
            RetrofitClient.instance.getBMI()
        } catch (e: Exception) {
            emptyList()
        }
    }
}