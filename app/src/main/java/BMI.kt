package Model

import androidx.annotation.DrawableRes

data class BMI(
    val nama: String,
    val deskripsi: String,
    val nilai: String,
    @DrawableRes val imageRes: Int
)