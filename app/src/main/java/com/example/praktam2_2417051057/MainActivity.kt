package com.example.praktam2_2417051057

import Model.BMIsource
import Model.BMI
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.praktam2_2417051057.ui.theme.PrakTAM2_2417051057Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM2_2417051057Theme {
                DaftarBMIScreen()
            }
        }
    }
}

@Composable
fun DaftarBMIScreen() {

    val favoriteMap = remember { mutableStateMapOf<String, Boolean>() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

            Text(
                text = "Klasifikasi BMI",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(BMIsource.dummyBMI) { bmi ->
                    BMIRowItem(bmi)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Semua Kategori BMI",
                style = MaterialTheme.typography.titleLarge
            )
        }

        items(BMIsource.dummyBMI) { bmi ->

            val isFav = favoriteMap[bmi.nama] ?: false

            DetailBMIScreen(
                bmi = bmi,
                isFavorite = isFav,
                onFavoriteClick = {
                    favoriteMap[bmi.nama] = !isFav
                }
            )
        }
    }
}

@Composable
fun BMIRowItem(bmi: BMI) {

    Card(
        modifier = Modifier.width(160.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column {

            Image(
                painter = painterResource(id = bmi.imageRes),
                contentDescription = bmi.nama,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Fit
            )

            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = bmi.nama,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = bmi.nilai,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun DetailBMIScreen(
    bmi: BMI,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column {

            Box {

                Image(
                    painter = painterResource(id = bmi.imageRes),
                    contentDescription = bmi.nama,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Fit
                )

                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector =
                            if (isFavorite) Icons.Filled.Favorite
                            else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint =
                            if (isFavorite) Color.Red
                            else Color.Gray
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = bmi.nama,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = bmi.deskripsi,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Nilai BMI: ${bmi.nilai}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cek BMI")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBMI() {
    PrakTAM2_2417051057Theme {
        DaftarBMIScreen()
    }
}