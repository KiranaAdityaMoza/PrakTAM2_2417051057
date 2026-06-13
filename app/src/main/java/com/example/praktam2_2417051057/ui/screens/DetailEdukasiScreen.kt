package com.example.praktam2_2417051057.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.praktam2_2417051057.data.model.BMI
import com.example.praktam2_2417051057.data.repository.BMIRepository

@Composable
fun DetailEdukasiWrapper(nama: String?, navController: NavHostController) {
    val repo = remember { BMIRepository() }
    var data by remember { mutableStateOf<BMI?>(null) }
    LaunchedEffect(nama) { data = repo.getBMI().find { it.nama == nama } }
    data?.let { bmi ->
        Column(modifier = Modifier.fillMaxSize().statusBarsPadding().verticalScroll(rememberScrollState())) {
            Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp)) {
                IconButton(onClick = {
                    navController.navigate("main?tab=2") {
                        popUpTo("main") { inclusive = true }
                    }
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            }

            AsyncImage(
                model = bmi.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(250.dp).padding(horizontal = 24.dp).clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(24.dp)) {
                Text(bmi.nama, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                Text("Nilai BMI: ${bmi.nilai}", color = Color(0xFF2196F3), fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))
                Text("Deskripsi", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(bmi.deskripsi, textAlign = TextAlign.Justify)

                if (!bmi.ciriCiri.isNullOrEmpty()) {
                    Spacer(Modifier.height(16.dp))
                    Text("Ciri-ciri", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    bmi.ciriCiri.forEach { Row(modifier = Modifier.padding(vertical = 2.dp)) { Text("• ", color = Color(0xFF4CAF50), fontWeight = FontWeight.Bold); Text(it) } }
                }
                if (!bmi.saran.isNullOrEmpty()) {
                    Spacer(Modifier.height(16.dp))
                    Text("Saran", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    bmi.saran.forEach { Row(modifier = Modifier.padding(vertical = 2.dp)) { Text("✓ ", color = Color(0xFF4CAF50), fontWeight = FontWeight.Bold); Text(it) } }
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
                    Text("Kembali")
                }
                Spacer(Modifier.height(32.dp))
            }
        }
    }
}
