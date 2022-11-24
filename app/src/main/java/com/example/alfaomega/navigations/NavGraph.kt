package com.example.alfaomega.navigations

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alfaomega.screens.ScreenHome
import com.example.alfaomega.screens.ScreenMenu

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraphSetup(
    navController: NavHostController
    ) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(
            route = Screens.Home.route,
        ) {
            ScreenHome(navController = navController)
        }

        composable(
            route = Screens.Menu.route,
        ){
//            LaunchedEffect(key1 = STORE_ID){
//                Log.d("debug", "Qris NavGraph")
//                paymentViewModel.getQR()
//            }
            ScreenMenu(navController = navController)
        }
    }
}