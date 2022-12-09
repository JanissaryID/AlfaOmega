package com.example.alfaomega.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alfaomega.components.TopBar
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenStoreList(navController: NavController, protoViewModel: ProtoViewModel) {

    TopBar(
        typeScreen = false,
        tittleScreen = "List Store",
        wallScreen = 5,
        navController = navController,
        screenBack = Screens.StoreProfile.route, protoViewModel = protoViewModel
    )
}

@Preview(showBackground = true)
@Composable
fun ViewStoreList() {
    AlfaOmegaTheme {
//        ScreenMenu(navController = rememberNavController())
    }
}