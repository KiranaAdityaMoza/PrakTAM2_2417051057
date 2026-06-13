package com.example.praktam2_2417051057.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktam2_2417051057.R
import com.example.praktam2_2417051057.data.model.UserSession
import java.util.Locale

@Composable
fun HomeScreen(user: UserSession, lastBmi: Double, lastCat: String, lastW: Double, lastH: Double, onStartCalc: () -> Unit) {
    val tip = when {
        lastBmi == 0.0 -> "Lakukan pengecekan BMI pertamamu sekarang!"
        lastBmi < 18.5 -> "Tips: Perbanyak asupan kalori dan protein sehat untuk mencapai berat ideal."
        lastBmi < 25.0 -> "Tips: Keren! BMI kamu normal. Jaga pola makan dan rutin berolahraga."
        else -> "Tips: Kurangi konsumsi gula dan lemak jenuh. Yuk, mulai rutin jalan kaki!"
    }

    Column(Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState())) {
        Text("Halo, ${user.nama} 👋", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("Semangat untuk hidup sehat!", color = Color.Gray)
        Spacer(Modifier.height(24.dp))

        Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White), border = BorderStroke(0.5.dp, Color.LightGray), elevation = CardDefaults.cardElevation(0.dp)) {
            Column(Modifier.padding(16.dp)) {
                Text("BMI Terakhir", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = if (lastBmi == 0.0) "-" else String.format(Locale.US, "%.1f", lastBmi), fontSize = 48.sp, fontWeight = FontWeight.Black, color = Color(0xFF2196F3))
                    Spacer(Modifier.width(16.dp))
                    Surface(color = if (lastCat == "Normal") Color(0xFF4CAF50) else Color(0xFFFF9800), shape = CircleShape) {
                        Text(text = lastCat, modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp), color = Color.White, fontSize = 12.sp)
                    }
                }
                Spacer(Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    Column {
                        Text("Berat", fontSize = 12.sp, color = Color.Gray)
                        Text("${if(lastW == 0.0) "-" else lastW.toInt()} kg", fontWeight = FontWeight.Bold)
                    }
                    Spacer(Modifier.width(32.dp))
                    Column {
                        Text("Tinggi", fontSize = 12.sp, color = Color.Gray)
                        Text("${if(lastH == 0.0) "-" else lastH.toInt()} cm", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF2196F3))) {
            Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("Mulai Hitung BMI", color = Color.White, fontWeight = FontWeight.Bold)
                    Text("Ketahui status kesehatanmu sekarang.", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                    Spacer(Modifier.height(12.dp))
                    Button(onClick = onStartCalc, colors = ButtonDefaults.buttonColors(containerColor = Color.White)) { Text("Hitung Sekarang", color = Color(0xFF2196F3)) }
                }
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(64.dp), tint = Color.White.copy(alpha = 0.2f))
            }
        }
        Spacer(Modifier.height(24.dp))
        Text("Tips Hari Ini", fontWeight = FontWeight.Bold)
        Card(Modifier.fillMaxWidth().padding(top = 8.dp), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.dashboard), contentDescription = null, modifier = Modifier.size(60.dp).clip(RoundedCornerShape(8.dp)), contentScale = ContentScale.Crop)
                Spacer(Modifier.width(16.dp)); Text(tip, fontSize = 14.sp)
            }
        }
    }
}
