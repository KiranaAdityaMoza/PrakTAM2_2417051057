package com.example.praktam2_2417051057.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
fun EdukasiScreen(navController: NavHostController) {
    val repo = remember { BMIRepository() }
    var list by remember { mutableStateOf<List<BMI>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        try {
            val res = repo.getBMI()
            list = res
            isError = res.isEmpty()
        } catch (e: Exception) { isError = true }
        finally { isLoading = false }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {
        Text("Edukasi BMI", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("Pelajari kategori BMI dan jaga kesehatanmu", color = Color.Gray)
        Spacer(Modifier.height(16.dp))
        if (isLoading) Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
        else if (isError) Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Gagal memuat data, periksa koneksi internet", color = Color.Red, textAlign = TextAlign.Center) }
        else {
            LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(list) { item ->
                    Card(modifier = Modifier.fillMaxWidth().clickable { navController.navigate("detail_edukasi/${item.nama}") }) {
                        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            AsyncImage(model = item.imageUrl, contentDescription = null, modifier = Modifier.size(60.dp).clip(RoundedCornerShape(8.dp)), contentScale = ContentScale.Crop)
                            Spacer(Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(item.nama, fontWeight = FontWeight.Bold)
                                Text("Rentang: ${item.nilai}", fontSize = 12.sp, color = Color.Gray)
                            }
                            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}
