package com.example.praktam2_2417051057

import Model.BMIsource
import Model.BMI
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.navigation.compose.*
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.praktam2_2417051057.ui.theme.PrakTAM2_2417051057Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM2_2417051057Theme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            DaftarBMIScreen(navController)
        }

        composable("detail/{nama}") { backStackEntry ->
            val nama = backStackEntry.arguments?.getString("nama")

            val bmi = BMIsource.dummyBMI.find {
                it.nama == nama
            }

            if (bmi != null) {
                DetailPage(bmi, navController)
            }
        }
    }
}

@Composable
fun DaftarBMIScreen(navController: NavHostController) {

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
                    BMIRowItem(bmi, navController)
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
                },
                onButtonClick = {
                    navController.navigate("detail/${bmi.nama}")
                }
            )
        }
    }
}

@Composable
fun BMIRowItem(bmi: BMI, navController: NavHostController) {

    Card(
        modifier = Modifier
            .width(160.dp)
            .clickable {
                navController.navigate("detail/${bmi.nama}")
            },
        shape = RoundedCornerShape(12.dp)
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

                Text(text = bmi.nama)
                Text(
                    text = bmi.nilai,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun DetailBMIScreen(
    bmi: BMI,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onButtonClick: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
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
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector =
                            if (isFavorite) Icons.Filled.Favorite
                            else Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {

                Text(text = bmi.nama)

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = bmi.deskripsi)

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = "Nilai BMI: ${bmi.nilai}")

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onButtonClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cek BMI")
                }
            }
        }
    }
}

@Composable
fun DetailPage(bmi: BMI, navController: NavHostController) {

    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.padding(16.dp)) {

            Image(
                painter = painterResource(id = bmi.imageRes),
                contentDescription = bmi.nama,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = bmi.nama,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = bmi.deskripsi)

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Nilai BMI: ${bmi.nilai}",
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        isLoading = true
                        delay(2000)
                        snackbarHostState.showSnackbar(
                            "Analisis BMI ${bmi.nama} selesai"
                        )
                        isLoading = false
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            ) {

                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Memproses...")
                } else {
                    Text("Analisis BMI")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kembali")
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBMI() {
    PrakTAM2_2417051057Theme {
        val navController = rememberNavController()
        DaftarBMIScreen(navController)
    }
}