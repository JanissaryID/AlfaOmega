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
import com.example.alfaomega.api.rules.RuleViewModel
import com.example.alfaomega.api.store.StoreViewModel
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.api.user.UserViewModel
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.screens.*
import com.example.alfaomega.screens.developer.ScreenHomeDeveloper
import com.example.alfaomega.screens.owner.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraphSetup(
    navController: NavHostController,
    menuViewModel: MenuViewModel,
    transactionViewModel: TransactionViewModel,
    machineViewModel: MachineViewModel,
    storeViewModel: StoreViewModel,
    protoViewModel: ProtoViewModel,
    ruleViewModel: RuleViewModel,
    userViewModel: UserViewModel
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
                    storeViewModel.GetStore()

                    TRANSACTION_SCREEN = true

                    TRANSACATION_IS_WASHER = false
                    TRANSACATION_IS_DRYER = false
                    NEW_TRANSACATION_CUSTOMER = ""
                }
                BUTTON_LOGIN_CLICKED = false
            }
            if(USER_TYPE == 0){
                ScreenHomeOwner(navController = navController, protoViewModel = protoViewModel)
            }
            else if(USER_TYPE == 1){
                ScreenHome(navController = navController, protoViewModel = protoViewModel)
            }
            else{
                ScreenHomeDeveloper(navController = navController, protoViewModel = protoViewModel)
            }
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
                storeViewModel.FetchStore()
                TRANSACTION_SCREEN = true
            }
            ScreenStoreList(navController = navController, protoViewModel = protoViewModel)
        }

        composable(
            route = Screens.TransactionList.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                transactionViewModel.getTransactionNow()
                TRANSACTION_SCREEN = true
            }
            ScreenTransactionList(navController = navController, protoViewModel = protoViewModel)
        }

        composable(
            route = Screens.Login.route,
        ){
//            LaunchedEffect(key1 = STORE_ID){
//                transactionViewModel.getTransactionNow()
//                TRANSACTION_SCREEN = true
//            }
            ScreenLogin(navController = navController, protoViewModel = protoViewModel)
        }

        composable(
            route = Screens.MenuOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                menuViewModel.getMenu()
                TRANSACTION_SCREEN = true
            }
            ScreenMenuOwner(navController = navController, protoViewModel = protoViewModel)
        }

        composable(
            route = Screens.TransactionOwner.route,
        ){
//            LaunchedEffect(key1 = STORE_ID){
//                menuViewModel.getMenu()
//                TRANSACTION_SCREEN = true
//            }
            ScreenTransactionOwner(navController = navController, protoViewModel = protoViewModel)
        }

        composable(
            route = Screens.MenuEditOwner.route,
        ){
//            LaunchedEffect(key1 = STORE_ID){
//                menuViewModel.getMenu()
//                TRANSACTION_SCREEN = true
//            }
            ScreenMenuEditOwner(navController = navController, protoViewModel = protoViewModel)
        }

        composable(
            route = Screens.RulesOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                ruleViewModel.getRules()
//                TRANSACTION_SCREEN = true
            }
            ScreenRulesOwner(navController = navController, protoViewModel = protoViewModel)
        }

        composable(
            route = Screens.UserOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                userViewModel.fetchUser()
//                TRANSACTION_SCREEN = true
            }
            ScreenUserOwner(navController = navController, protoViewModel = protoViewModel)
        }

        composable(
            route = Screens.RulesEditOwner.route,
        ){
            ScreenRulesEditOwner(navController = navController, protoViewModel = protoViewModel)
        }

        composable(
            route = Screens.UserEditOwner.route,
        ){
            ScreenUserEditOwner(navController = navController, protoViewModel = protoViewModel)
        }
    }
}