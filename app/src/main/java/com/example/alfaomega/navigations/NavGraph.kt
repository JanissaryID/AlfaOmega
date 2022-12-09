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
import com.example.alfaomega.*
import com.example.alfaomega.api.machine.MachineViewModel
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.api.store.StoreViewModel
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.screens.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraphSetup(
    navController: NavHostController,
    menuViewModel: MenuViewModel,
    transactionViewModel: TransactionViewModel,
    machineViewModel: MachineViewModel,
    storeViewModel: StoreViewModel,
    protoViewModel: ProtoViewModel
    ) {

    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(
            route = Screens.Home.route,
        ) {
            LaunchedEffect(key1 = STORE_ID){
                if (!STORE_ID.isNullOrEmpty()){
                    transactionViewModel.getTransactionActive()
                    menuViewModel.getTime()

                    TRANSACTION_SCREEN = true

                    TRANSACATION_IS_WASHER = false
                    TRANSACATION_IS_DRYER = false
                    NEW_TRANSACATION_CUSTOMER = ""
                }
            }
            ScreenHome(navController = navController, protoViewModel = protoViewModel)
        }

        composable(
            route = Screens.Menu.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                menuViewModel.getMenu()
                TRANSACTION_SCREEN = true
            }
            ScreenMenu(navController = navController, protoViewModel = protoViewModel)
        }

        composable(
            route = Screens.DetailTransaction.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                MACHINE_SCREEN = true
            }
            ScreenDetailTransaction(navController = navController, protoViewModel = protoViewModel)
        }

        composable(
            route = Screens.StoreProfile.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                TRANSACTION_SCREEN = true
            }
            ScreenStore(navController = navController, protoViewModel = protoViewModel)
        }

        composable(
            route = Screens.Machine.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                machineViewModel.getMachine()
                TRANSACTION_SCREEN = true
            }
            ScreenMachine(navController = navController, protoViewModel = protoViewModel)
        }

        composable(
            route = Screens.Store.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                storeViewModel.getStore()
                TRANSACTION_SCREEN = true
            }
            ScreenStoreList(navController = navController, protoViewModel = protoViewModel)
        }
    }
}