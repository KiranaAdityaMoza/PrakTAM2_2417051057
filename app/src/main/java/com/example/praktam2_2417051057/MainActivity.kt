package com.example.praktam2_2417051057

import Model.BMI
import com.example.praktam2_2417051057.network.RetrofitClient
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import coil.compose.AsyncImage
import com.example.praktam2_2417051057.ui.theme.PrakTAM2_2417051057Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    var bmiList by remember {
        mutableStateOf<List<BMI>>(emptyList())
    }

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {

            DaftarBMIScreen(
                navController = navController,
                onBMILoaded = {
                    bmiList = it
                }
            )
        }

        composable("detail/{nama}") { backStackEntry ->

            val nama =
                backStackEntry.arguments?.getString("nama")

            val bmi = bmiList.find {
                it.nama == nama
            }

            if (bmi != null) {
                DetailPage(bmi, navController)
            }
        }
    }
}

@Composable
fun DaftarBMIScreen(
    navController: NavHostController,
    onBMILoaded: (List<BMI>) -> Unit = {}
) {

    var bmiList by remember {
        mutableStateOf<List<BMI>>(emptyList())
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    var isError by remember {
        mutableStateOf(false)
    }

    val favoriteMap = remember {
        mutableStateMapOf<String, Boolean>()
    }

    LaunchedEffect(Unit) {

        try {

            bmiList = RetrofitClient.instance.getBMI()

            onBMILoaded(bmiList)

            isLoading = false
            isError = false

        } catch (e: Exception) {

            isLoading = false
            isError = true
        }
    }

    if (isLoading) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator()
        }

    } else if (isError || bmiList.isEmpty()) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Gagal Memuat Data",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Pastikan koneksi internet menyala"
                )
            }
        }

    } else {

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
                    horizontalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {

                    items(bmiList) { bmi ->

                        BMIRowItem(
                            bmi = bmi,
                            navController = navController
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Semua Kategori BMI",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            items(bmiList) { bmi ->

                val isFav =
                    favoriteMap[bmi.nama] ?: false

                DetailBMIScreen(
                    bmi = bmi,
                    isFavorite = isFav,
                    onFavoriteClick = {
                        favoriteMap[bmi.nama] = !isFav
                    },
                    onButtonClick = {
                        navController.navigate(
                            "detail/${bmi.nama}"
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun BMIRowItem(
    bmi: BMI,
    navController: NavHostController
) {

    Card(
        modifier = Modifier
            .width(160.dp)
            .clickable {
                navController.navigate(
                    "detail/${bmi.nama}"
                )
            },
        shape = RoundedCornerShape(12.dp)
    ) {

        Column {

            AsyncImage(
                model = bmi.imageUrl,
                contentDescription = bmi.nama,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {

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

                AsyncImage(
                    model = bmi.imageUrl,
                    contentDescription = bmi.nama,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier.align(
                        Alignment.TopEnd
                    )
                ) {

                    Icon(
                        imageVector =
                            if (isFavorite)
                                Icons.Filled.Favorite
                            else
                                Icons.Outlined.FavoriteBorder,

                        contentDescription = null,

                        tint =
                            if (isFavorite)
                                Color.Red
                            else
                                Color.Gray
                    )
                }
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = bmi.nama,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = bmi.deskripsi)

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Nilai BMI: ${bmi.nilai}",
                    color = MaterialTheme.colorScheme.primary
                )

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
fun DetailPage(
    bmi: BMI,
    navController: NavHostController
) {

    var isLoading by remember {
        mutableStateOf(false)
    }

    val coroutineScope =
        rememberCoroutineScope()

    val snackbarHostState =
        remember {
            SnackbarHostState()
        }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            AsyncImage(
                model = bmi.imageUrl,
                contentDescription = bmi.nama,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

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

            Spacer(modifier = Modifier.height(24.dp))

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

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Text("Memproses...")

                } else {

                    Text("Analisis BMI")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Kembali")
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(
                Alignment.Center
            )
        )
    }
}