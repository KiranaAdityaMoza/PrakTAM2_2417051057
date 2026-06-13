package com.example.praktam2_2417051057.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.util.Locale

@Composable
fun ResultScreen(bmi: Double, category: String, weight: Double, height: Double, navController: NavHostController) {
    val info = when(category) {
        "Normal" -> "Berat badan kamu ideal. Pertahankan pola hidup sehatmu! 💪"
        "Underweight" -> "Berat badan kamu kurang. Yuk, perbanyak nutrisi! 🥗"
        else -> "Berat badan kamu berlebih. Mari rutin olahraga dan jaga makan! 🏃‍♂️"
    }
    val circleColor = if (category == "Normal") Color(0xFF4CAF50) else Color(0xFFFF9800)

    Column(Modifier.fillMaxSize().statusBarsPadding().padding(horizontal = 24.dp).verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    navController.navigate("main?tab=1") {
                        popUpTo("main") { inclusive = true }
                    }
                },
                modifier = Modifier.offset(x = (-12).dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            Text("Hasil BMI", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(32.dp))
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(200.dp)) {
            CircularProgressIndicator(progress = { (bmi/40.0).toFloat().coerceIn(0f, 1f) }, modifier = Modifier.fillMaxSize(), strokeWidth = 12.dp, color = circleColor, trackColor = Color.LightGray)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(String.format(Locale.US, "%.1f", bmi), fontSize = 48.sp, fontWeight = FontWeight.Black)
                Text(category, color = circleColor, fontWeight = FontWeight.Medium)
            }
        }
        Spacer(Modifier.height(48.dp))
        Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))) {
            Column(Modifier.padding(16.dp)) { Text("Informasi", fontWeight = FontWeight.Bold); Text(info, fontSize = 14.sp) }
        }
        Spacer(Modifier.height(24.dp))
        Text("Rincian", modifier = Modifier.align(Alignment.Start), fontWeight = FontWeight.Bold)
        Card(modifier = Modifier.fillMaxWidth().padding(top = 8.dp), colors = CardDefaults.cardColors(containerColor = Color.White), border = BorderStroke(1.dp, Color.LightGray)) {
            Column(modifier = Modifier.padding(16.dp)) {
                ResultDetailRow("Berat Badan", "${weight.toInt()} kg")
                ResultDetailRow("Tinggi Badan", "${height.toInt()} cm")
                ResultDetailRow("BMI", String.format(Locale.US, "%.1f", bmi))
                ResultDetailRow("Kategori", category, isLast = true)
            }
        }
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = {
                navController.navigate("main?tab=2") {
                    popUpTo("main") { inclusive = true }
                }
            },
            Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Lihat Kategori BMI")
        }
        Spacer(Modifier.height(32.dp))
    }
}

@Composable
fun ResultDetailRow(label: String, value: String, isLast: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(label, color = Color.Gray); Text(value, fontWeight = FontWeight.Bold)
    }
    if (!isLast) HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), thickness = 0.5.dp)
}
