package com.example.praktam2_2417051057

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.praktam2_2417051057.data.model.UserSession
import com.example.praktam2_2417051057.ui.screens.*
import com.example.praktam2_2417051057.ui.theme.PrakTAM2_2417051057Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM2_2417051057Theme {
                val navController = rememberNavController()

                var userSession by remember { mutableStateOf(UserSession()) }
                var lastBmiValue by remember { mutableStateOf(0.0) }
                var lastBmiCategory by remember { mutableStateOf("Belum ada data") }
                var lastWeight by remember { mutableStateOf(0.0) }
                var lastHeight by remember { mutableStateOf(0.0) }

                NavHost(
                    navController = navController,
                    startDestination = "splash",
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {
                    composable("splash") { SplashScreen(navController) }

                    composable("login") {
                        LoginScreen { n, u, g ->
                            userSession = UserSession(n, u, g)
                            navController.navigate("main") { popUpTo("login") { inclusive = true } }
                        }
                    }

                    composable(
                        "main?tab={tab}",
                        arguments = listOf(navArgument("tab") {
                            type = NavType.IntType
                            defaultValue = 0
                        })
                    ) { backStackEntry ->
                        val initialTab = backStackEntry.arguments?.getInt("tab") ?: 0
                        MainContainer(navController, userSession, lastBmiValue, lastBmiCategory, lastWeight, lastHeight, initialTab)
                    }

                    composable("main") {
                        MainContainer(navController, userSession, lastBmiValue, lastBmiCategory, lastWeight, lastHeight, 0)
                    }

                    composable("result/{bmi}/{cat}/{weight}/{height}") { backStackEntry ->
                        val bmiArg = backStackEntry.arguments?.getString("bmi")?.toDoubleOrNull() ?: 0.0
                        val catArg = backStackEntry.arguments?.getString("cat") ?: "-"
                        val weightArg = backStackEntry.arguments?.getString("weight")?.toDoubleOrNull() ?: 0.0
                        val heightArg = backStackEntry.arguments?.getString("height")?.toDoubleOrNull() ?: 0.0

                        LaunchedEffect(bmiArg) {
                            lastBmiValue = bmiArg
                            lastBmiCategory = catArg
                            lastWeight = weightArg
                            lastHeight = heightArg
                        }

                        ResultScreen(bmiArg, catArg, weightArg, heightArg, navController)
                    }

                    composable("detail_edukas{nama}") { backStackEntry ->
                        val nama = backStackEntry.arguments?.getString("nama")
                        DetailEdukasiWrapper(nama, navController)
                    }
                }
            }
        }
    }
}
