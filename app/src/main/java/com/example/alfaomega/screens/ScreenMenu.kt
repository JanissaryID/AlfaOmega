package com.example.alfaomega.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alfaomega.TRANSACTION_SCREEN
import com.example.alfaomega.components.TopBar
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@Composable
fun ScreenMenu(navController: NavController) {

//    TRANSACTION_SCREEN = true

    TopBar(
        typeScreen = false,
        tittleScreen = "Menu",
        wallScreen = 1,
        navController = navController,
        screenBack = Screens.Home.route
    )
}

@Preview(showBackground = true)
@Composable
fun ViewMenu() {
    AlfaOmegaTheme {
        ScreenMenu(navController = rememberNavController())
    }
}