package com.example.praktam2_2417051057.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.util.Locale

@Composable
fun BmiCalculatorScreen(navController: NavHostController) {
    var b by remember { mutableStateOf("") }
    var t by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(24.dp)) {
        Text("Hitung BMI", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("Masukkan berat dan tinggi badanmu", color = Color.Gray)
        Spacer(Modifier.height(32.dp))
        OutlinedTextField(value = b, onValueChange = { b = it }, label = { Text("Berat Badan (kg)") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value = t, onValueChange = { t = it }, label = { Text("Tinggi Badan (cm)") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Spacer(Modifier.height(32.dp))
        Button(onClick = {
            val weight = b.toDoubleOrNull() ?: 0.0
            val height = t.toDoubleOrNull() ?: 0.0
            if (weight > 0 && height > 0) {
                val res = weight / ((height/100)*(height/100))
                val cat = when {
                    res < 18.5 -> "Underweight"
                    res < 25.0 -> "Normal"
                    res < 30.0 -> "Overweight"
                    res < 35.0 -> "Obesitas"
                    else -> "Extreme Obesitas"
                }
                navController.navigate("result/${String.format(Locale.US, "%.1f", res)}/$cat/$weight/$height")
            }
        }, Modifier.fillMaxWidth().height(50.dp), shape = RoundedCornerShape(12.dp)) { Text("Hitung BMI") }
    }
}
