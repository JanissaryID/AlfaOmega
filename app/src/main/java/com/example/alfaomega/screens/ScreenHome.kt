package com.example.alfaomega.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alfaomega.components.TopBar
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@Composable
fun ScreenHome(navController: NavController) {
    TopBar(
        typeScreen = true,
        tittleScreen = "Transaction Active",
        wallScreen = 0,
        navController = navController,
        screenBack = Screens.Home.route
    )
}

@Preview(showBackground = true)
@Composable
fun ViewHome() {
    AlfaOmegaTheme {
        ScreenHome(navController = rememberNavController())
    }
}