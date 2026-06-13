package com.example.praktam2_2417051057.data.model

import com.google.gson.annotations.SerializedName

data class BMI(
    @SerializedName("nama")
    val nama: String,

    @SerializedName("deskripsi")
    val deskripsi: String,

    @SerializedName("nilai")
    val nilai: String,

    @SerializedName("image_url")
    val imageUrl: String,

    // Menggunakan "ciri-ciri" sesuai JSON yang kamu kirim
    @SerializedName("ciri_ciri")
    val ciriCiri: List<String>? = null,

    @SerializedName("saran")
    val saran: List<String>? = null
)