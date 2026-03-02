package Model

import com.example.praktam2_2417051057.R

object BMIsource {
    val dummyBMI = listOf(
        BMI("Underweight", "Berat badan kurang", "< 18.5", R.drawable.under_bmi),
        BMI("Normal", "Berat badan normal", "18.5 - 24.9", R.drawable.normal_bmi),
        BMI("Overweight", "Berat badan berlebih", "25 - 29.9", R.drawable.over_bmi),
        BMI("Obesitas", "Obesitas tingkat 1", "30 - 34.9", R.drawable.obes_bmi),
        BMI("Extreme Obesitas", "Obesitas tingkat tinggi", ">= 35", R.drawable.extrem_bmi)
    )
}