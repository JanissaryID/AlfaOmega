package com.example.alfaomega.navigations

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alfaomega.MENU_LIST_TITAN_RESPONSE
import com.example.alfaomega.STORE_ID
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.screens.ScreenDetailTransaction
import com.example.alfaomega.screens.ScreenHome
import com.example.alfaomega.screens.ScreenMenu
import com.example.alfaomega.screens.ScreenStore

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraphSetup(
    navController: NavHostController,
    menuViewModel: MenuViewModel,
    transactionViewModel: TransactionViewModel
    ) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(
            route = Screens.Home.route,
        ) {
            LaunchedEffect(key1 = STORE_ID){
                transactionViewModel.getTransactionActive()
            }
            ScreenHome(navController = navController)
        }

        composable(
            route = Screens.Menu.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                menuViewModel.getMenu()
            }
            ScreenMenu(navController = navController)
        }

        composable(
            route = Screens.DetailTransaction.route,
        ){
//            LaunchedEffect(key1 = STORE_ID){
//                Log.d("debug", "Qris NavGraph")
//                paymentViewModel.getQR()
//            }
            ScreenDetailTransaction(navController = navController)
        }

        composable(
            route = Screens.StoreProfile.route,
        ){
//            LaunchedEffect(key1 = STORE_ID){
//                Log.d("debug", "Qris NavGraph")
//                paymentViewModel.getQR()
//            }
            ScreenStore(navController = navController)
        }
    }
}