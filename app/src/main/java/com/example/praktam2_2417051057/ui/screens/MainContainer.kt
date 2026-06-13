package com.example.praktam2_2417051057.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.praktam2_2417051057.data.model.UserSession

@Composable
fun MainContainer(navController: NavHostController, user: UserSession, lastV: Double, lastC: String, lastW: Double, lastH: Double, initialTab: Int = 0) {
    var selectedTab by remember(initialTab) { mutableIntStateOf(initialTab) }
    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 0.dp
            ) {
                val items = listOf("Home" to Icons.Default.Home, "BMI" to Icons.Default.PlayArrow, "Edukasi" to Icons.Default.Info, "Profil" to Icons.Default.Person)
                items.forEachIndexed { i, (label, icon) ->
                    NavigationBarItem(
                        selected = selectedTab == i,
                        onClick = { selectedTab = i },
                        label = { Text(label) },
                        icon = { Icon(icon, null) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF2196F3),
                            unselectedIconColor = Color.Gray,
                            selectedTextColor = Color(0xFF2196F3),
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.White
                        )
                    )
                }
            }
        }
    ) { p ->
        Box(Modifier.padding(p).statusBarsPadding()) {
            when (selectedTab) {
                0 -> HomeScreen(user, lastV, lastC, lastW, lastH) { selectedTab = 1 }
                1 -> BmiCalculatorScreen(navController)
                2 -> EdukasiScreen(navController)
                3 -> ProfileScreen(user) { navController.navigate("login") { popUpTo(0) } }
            }
        }
    }
}
