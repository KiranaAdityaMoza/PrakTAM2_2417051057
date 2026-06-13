package com.example.praktam2_2417051057.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(onLogin: (String, String, String) -> Unit) {
    var n by remember { mutableStateOf("") }
    var u by remember { mutableStateOf("") }
    var g by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding().padding(24.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.size(80.dp), tint = Color(0xFF2196F3))
        Text("Selamat Datang!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Isi data diri untuk memulai", color = Color.Gray)
        Spacer(Modifier.height(32.dp))
        OutlinedTextField(value = n, onValueChange = { n = it }, label = { Text("Nama Lengkap") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value = u, onValueChange = { u = it }, label = { Text("Umur (Tahun)") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Spacer(Modifier.height(16.dp))
        Text("Jenis Kelamin", modifier = Modifier.align(Alignment.Start), fontWeight = FontWeight.Medium)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { g = "Perempuan" }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = if (g == "Perempuan") Color(0xFF2196F3) else Color.LightGray)) { Text("Perempuan") }
            Button(onClick = { g = "Laki-laki" }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = if (g == "Laki-laki") Color(0xFF2196F3) else Color.LightGray)) { Text("Laki-laki") }
        }
        Spacer(Modifier.height(32.dp))
        Button(onClick = { if (n.isNotEmpty() && u.isNotEmpty() && g.isNotEmpty()) onLogin(n, u, g) }, modifier = Modifier.fillMaxWidth().height(50.dp), shape = RoundedCornerShape(12.dp), enabled = (n.isNotEmpty() && u.isNotEmpty() && g.isNotEmpty())) { Text("Mulai") }
    }
}
