package com.example.alfaomega.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alfaomega.TRANSACATION_STATUS_MACHINE
import com.example.alfaomega.components.TopBar
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenMachine(navController: NavController, protoViewModel: ProtoViewModel) {
    TopBar(
        typeScreen = false,
        tittleScreen = if(TRANSACATION_STATUS_MACHINE == 0) "Wash Machine" else "Dry Machine",
        wallScreen = 4,
        navController = navController,
        screenBack = Screens.DetailTransaction.route,
        protoViewModel = protoViewModel
    )
}

@Preview(showBackground = true)
@Composable
fun ViewMachine() {
    AlfaOmegaTheme {
//        ScreenMachine(navController = rememberNavController())
    }
}