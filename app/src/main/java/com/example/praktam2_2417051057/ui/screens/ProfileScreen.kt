package com.example.praktam2_2417051057.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
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

@Composable
fun ProfileScreen(user: UserSession, onLogout: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Profil Saya", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp))
        Image(painter = painterResource(id = if (user.gender == "Perempuan") R.drawable.icon_perempuan else R.drawable.icon_laki), contentDescription = null, modifier = Modifier.size(120.dp).clip(CircleShape).border(2.dp, Color(0xFF2196F3), CircleShape), contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.height(16.dp)); Text(user.nama, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Semangat hidup sehat!", color = Color.Gray)
        Spacer(modifier = Modifier.height(32.dp))
        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White), border = BorderStroke(0.5.dp, Color.LightGray), elevation = CardDefaults.cardElevation(0.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                ProfileInfoRow(Icons.Default.Person, "Nama", user.nama)
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                ProfileInfoRow(Icons.Default.DateRange, "Umur", "${user.umur} Tahun")
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                ProfileInfoRow(Icons.Default.Face, "Jenis Kelamin", user.gender)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(onClick = onLogout, modifier = Modifier.fillMaxWidth().height(50.dp), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)) { Text("Keluar") }
    }
}

@Composable
fun ProfileInfoRow(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(16.dp)); Column { Text(label, fontSize = 12.sp, color = Color.Gray); Text(value, fontWeight = FontWeight.Medium) }
    }
}
