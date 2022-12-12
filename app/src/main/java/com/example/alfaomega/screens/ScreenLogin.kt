package com.example.alfaomega.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.alfaomega.components.TopBar
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenLogin(navController: NavController, protoViewModel: ProtoViewModel) {

//    TRANSACTION_SCREEN = true

    TopBar(
        typeScreen = false,
        tittleScreen = "Login",
        wallScreen = 7,
        navController = navController,
        screenBack = Screens.Home.route,
        protoViewModel = protoViewModel
    )
}

@Preview(showBackground = true)
@Composable
fun ViewLogin() {
    AlfaOmegaTheme {
//        ScreenStore(navController = rememberNavController())
    }
}