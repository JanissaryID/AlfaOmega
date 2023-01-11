package com.example.alfaomega.navigations

import android.Manifest
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
import com.example.alfaomega.api.log.LogViewModel
import com.example.alfaomega.api.machine.MachineViewModel
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.api.problem.ProblemViewModel
import com.example.alfaomega.api.rules.RuleViewModel
import com.example.alfaomega.api.store.StoreViewModel
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.api.user.UserViewModel
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.screens.*
import com.example.alfaomega.screens.admin.ScreenBluetooth
import com.example.alfaomega.screens.admin.ScreenReportMachine
import com.example.alfaomega.screens.developer.ScreenHomeDeveloper
import com.example.alfaomega.screens.owner.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NavGraphSetup(
    navController: NavHostController,
    menuViewModel: MenuViewModel,
    transactionViewModel: TransactionViewModel,
    machineViewModel: MachineViewModel,
    storeViewModel: StoreViewModel,
    protoViewModel: ProtoViewModel,
    ruleViewModel: RuleViewModel,
    userViewModel: UserViewModel,
    logViewModel: LogViewModel,
    bluetoothViewModel: BluetoothViewModel,
    problemViewModel: ProblemViewModel
    ) {

    val context = LocalContext.current

    val multiplePermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_ADVERTISE,
            Manifest.permission.BLUETOOTH_CONNECT
        )
    )

    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(
            route = Screens.Home.route,
        ) {
            LaunchedEffect(key1 = STORE_ID){
                if (!STORE_ID.isNullOrEmpty()){
                    transactionViewModel.getTransactionActive()
//                    menuViewModel.getTime()
                    storeViewModel.GetStore()

                    TRANSACTION_SCREEN = true

                    TRANSACATION_IS_WASHER = false
                    TRANSACATION_IS_DRYER = false
                    NEW_TRANSACATION_CUSTOMER = ""
                    TRANSACATION_PAYMENT = ""
                }
                BUTTON_LOGIN_CLICKED = false
            }
            if(USER_TYPE == 3){
                ScreenHome(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
            }
            else if(USER_TYPE == 2){
                ScreenHomeDeveloper(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
            }
            else if(USER_TYPE == 1){
                ScreenHomeOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
            }
            else{
                ScreenHome(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
            }
        }

        composable(
            route = Screens.Menu.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                menuViewModel.getMenu()
                TRANSACTION_SCREEN = true
            }
            ScreenMenu(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.DetailTransaction.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                MACHINE_SCREEN = true
            }
            ScreenDetailTransaction(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.StoreProfile.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                TRANSACTION_SCREEN = true
                bluetoothViewModel.showPairedDevice(context = MY_CONTEXT!!, multiplePermissionState = multiplePermissionState)
            }
            ScreenStore(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.Machine.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                machineViewModel.getMachine()
                TRANSACTION_SCREEN = true
            }
            ScreenMachine(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.Store.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                storeViewModel.FetchStore()
                TRANSACTION_SCREEN = true
            }
            ScreenStoreList(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.TransactionList.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                transactionViewModel.getTransactionNow()
                TRANSACTION_SCREEN = true
            }
            ScreenTransactionList(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.Login.route,
        ){
//            LaunchedEffect(key1 = STORE_ID){
//                transactionViewModel.getTransactionNow()
//                TRANSACTION_SCREEN = true
//            }
            ScreenLogin(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.Bluetooth.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                bluetoothViewModel.showPairedDevice(context = MY_CONTEXT!!, multiplePermissionState = multiplePermissionState)
            }
            ScreenBluetooth(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.MenuOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                menuViewModel.getMenu()
                TRANSACTION_SCREEN = true
            }
            ScreenMenuOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.TransactionOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                transactionViewModel.getTransactionNowDate()
                logViewModel.fetchLog()
                TRANSACTION_SCREEN = true
            }
            ScreenTransactionOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.MenuEditOwner.route,
        ){
//            LaunchedEffect(key1 = STORE_ID){
//                menuViewModel.getMenu()
//                TRANSACTION_SCREEN = true
//            }
            ScreenMenuEditOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.RulesOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                ruleViewModel.getRules()
//                TRANSACTION_SCREEN = true
            }
            ScreenRulesOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.UserOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                userViewModel.fetchUser()
//                TRANSACTION_SCREEN = true
            }
            ScreenUserOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.RulesEditOwner.route,
        ){
            ScreenRulesEditOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.UserEditOwner.route,
        ){
            ScreenUserEditOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.ReportMachine.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                problemViewModel.fetchProblem()
//                TRANSACTION_SCREEN = true
            }
            ScreenReportMachine(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }
    }
}