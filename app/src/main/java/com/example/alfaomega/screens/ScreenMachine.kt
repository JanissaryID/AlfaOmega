package com.example.alfaomega.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alfaomega.components.TopBar
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@Composable
fun ScreenMachine(navController: NavController) {
    TopBar(
        typeScreen = false,
        tittleScreen = "Pick Machine",
        wallScreen = 4,
        navController = navController,
        screenBack = Screens.DetailTransaction.route
    )
}

@Preview(showBackground = true)
@Composable
fun ViewMachine() {
    AlfaOmegaTheme {
        ScreenMachine(navController = rememberNavController())
    }
}