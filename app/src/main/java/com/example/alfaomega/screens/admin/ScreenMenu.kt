package com.example.alfaomega.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alfaomega.TRANSACTION_SCREEN
import com.example.alfaomega.components.TopBar
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenMenu(navController: NavController, protoViewModel: ProtoViewModel) {

    TopBar(
        typeScreen = false,
        tittleScreen = "Menu",
        wallScreen = 1,
        navController = navController,
        screenBack = Screens.Home.route,
        protoViewModel = protoViewModel
    )
}

@Preview(showBackground = true)
@Composable
fun ViewMenu() {
    AlfaOmegaTheme {
//        ScreenMenu(navController = rememberNavController())
    }
}