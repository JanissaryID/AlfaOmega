package com.example.alfaomega.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alfaomega.components.TopBar
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@Composable
fun ScreenDetailTransaction(navController: NavController) {
    TopBar(
        typeScreen = false,
        tittleScreen = "Detail Transaction",
        wallScreen = 2,
        navController = navController,
        screenBack = Screens.Home.route
    )
}

@Preview(showBackground = true)
@Composable
fun ViewDetailTransaction() {
    AlfaOmegaTheme {
        ScreenDetailTransaction(navController = rememberNavController())
    }
}