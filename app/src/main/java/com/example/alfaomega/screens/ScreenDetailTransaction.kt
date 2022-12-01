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
fun ScreenDetailTransaction(
    navController: NavController
) {
    TopBar(
        typeScreen = false,
        tittleScreen = if(!TRANSACTION_SCREEN) "Detail Transaction" else "Create Transaction",
        wallScreen = 2,
        navController = navController,
        screenBack = if (!TRANSACTION_SCREEN) Screens.Home.route else Screens.Menu.route,
    )
}

@Preview(showBackground = true)
@Composable
fun ViewDetailTransaction() {
    AlfaOmegaTheme {
        ScreenDetailTransaction(navController = rememberNavController())
    }
}