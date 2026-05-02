package Model

import com.google.gson.annotations.SerializedName

data class BMI(

    @SerializedName("nama")
    val nama: String,

    @SerializedName("deskripsi")
    val deskripsi: String,

    @SerializedName("nilai")
    val nilai: String,

    @SerializedName("image_url")
    val imageUrl: String

)