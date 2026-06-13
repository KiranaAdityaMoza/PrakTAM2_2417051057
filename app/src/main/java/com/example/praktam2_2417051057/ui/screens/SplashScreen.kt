package com.example.praktam2_2417051057.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.praktam2_2417051057.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("login") { popUpTo("splash") { inclusive = true } }
    }
    Box(modifier = Modifier.fillMaxSize().statusBarsPadding(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(id = R.drawable.bmi_logo), null, Modifier.size(150.dp))
            Spacer(Modifier.height(16.dp))
            Text("BMI Tracker", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2196F3))
            Text("Sehat hari ini, lebih baik di masa depan", color = Color.Gray)
        }
    }
}
