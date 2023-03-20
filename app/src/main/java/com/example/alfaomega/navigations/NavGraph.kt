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
import com.example.alfaomega.api.expenses.ExpensesViewModel
import com.example.alfaomega.api.income.IncomeViewModel
import com.example.alfaomega.api.log.LogViewModel
import com.example.alfaomega.api.machine.MachineViewModel
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.api.problem.ProblemViewModel
import com.example.alfaomega.api.qr.QrViewModel
import com.example.alfaomega.api.rules.RuleViewModel
import com.example.alfaomega.api.store.StoreViewModel
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.api.user.UserViewModel
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.screens.*
import com.example.alfaomega.screens.admin.ScreenBluetooth
import com.example.alfaomega.screens.admin.ScreenExpenses
import com.example.alfaomega.screens.admin.ScreenReportMachine
import com.example.alfaomega.screens.developer.ScreenHomeDeveloper
import com.example.alfaomega.screens.developer.ScreenOwnerDeveloper
import com.example.alfaomega.screens.developer.ScreenStoreEditDeveloper
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
    problemViewModel: ProblemViewModel,
    incomeViewModel: IncomeViewModel,
    expensesViewModel: ExpensesViewModel,
    qrViewModel: QrViewModel
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

                SCREEN_ACTIVE_NOW = Screens.Home.route

                if (!STORE_ID.isNullOrEmpty()){
                    STAT_GET_DATA = true
                    transactionViewModel.getTransactionActive()

//                    STATUS_LOGIN_LOGOUT = false

                    TRANSACATION_IS_WASHER = false
                    TRANSACATION_IS_DRYER = false
                    NEW_TRANSACATION_CUSTOMER = ""
                    TRANSACATION_PAYMENT = ""

                    MENU_MACHINE_CLASS = 0
                }
                BUTTON_LOGIN_CLICKED = false
            }
            if(USER_TYPE == 3){
                if(STORE_LIST_RESPONSE.isNullOrEmpty() && STORE_STATE <= 1){
                    storeViewModel.CoroutineFetchStore()
                }

                if(STORE_NAME.isNullOrEmpty() && !STORE_ID.isNullOrEmpty()){
                    storeViewModel.GetStore()
                }

                ScreenHome(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
            }
            else if(USER_TYPE == 2){
                ScreenHomeDeveloper(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
            }
            else if(USER_TYPE == 1){
                PROBLEM_MACHINE_STATE_SCREEN = false

                if(LIST_RULE.isNullOrEmpty() && RULE_STATE <= 1){
                    ruleViewModel.getRules()
                }

                if(LIST_USER.isNullOrEmpty() && USER_STATE <= 1){
                    userViewModel.fetchUser()
                }

                if(STORE_LIST_RESPONSE.isNullOrEmpty() && STORE_STATE <= 1){
                    storeViewModel.CoroutineFetchStore()
                }

                if (QR_DATA.id.isNullOrEmpty() && QR_STATE <= 1){
                    qrViewModel.fetchQrOwner()
                }

                incomeViewModel.CoroutineFetchIncomeOwner()

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

                SCREEN_ACTIVE_NOW = Screens.Menu.route

                STAT_GET_DATA = false
                menuViewModel.getMenu()
                TRANSACTION_SCREEN = true
            }
            ScreenMenu(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.DetailTransaction.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.DetailTransaction.route

                STAT_GET_DATA = false
                MACHINE_SCREEN = true
            }
            ScreenDetailTransaction(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.StoreProfile.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.StoreProfile.route

                STAT_GET_DATA = false
//                TRANSACTION_SCREEN = true

                if(STORE_NAME.isNullOrEmpty()){
                    storeViewModel.GetStore()
                }

            }
            ScreenStore(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.Machine.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.Machine.route

                STAT_GET_DATA = false
                if(USER_TYPE == 2){
                    machineViewModel.getMachineList()
                }
                else{
                    machineViewModel.getMachine()
                }
//                TRANSACTION_SCREEN = true
            }
            ScreenMachine(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.Store.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.Store.route

                STAT_GET_DATA = false

                storeViewModel.FetchStore()
//                storeViewModel.CoroutineFetchStore()
//                TRANSACTION_SCREEN = true
            }
            ScreenStoreList(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.TransactionList.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.TransactionList.route

                STAT_GET_DATA = false
                transactionViewModel.getTransactionNow()
//                TRANSACTION_SCREEN = true
            }
            ScreenTransactionList(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.Login.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.Login.route

                STORE_LIST_RESPONSE.clear()

                STAT_GET_DATA = false
            }
            ScreenLogin(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.Bluetooth.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.Bluetooth.route

                STAT_GET_DATA = false
                bluetoothViewModel.showPairedDevice(context = MY_CONTEXT!!, multiplePermissionState = multiplePermissionState)
            }
            ScreenBluetooth(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.ExpensesStore.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.ExpensesStore.route

                if(STORE_NAME.isNullOrEmpty()){
                    storeViewModel.GetStore()
                }

            }
            ScreenExpenses(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.MenuOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.MenuOwner.route

                STAT_GET_DATA = false

//                TRANSACTION_SCREEN = true
                CLASS_MENU_EDIT_STRING = ""

                menuViewModel.getMenu()
            }
            ScreenMenuOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.TransactionOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.TransactionOwner.route

                STAT_GET_DATA = false
                transactionViewModel.getTransactionNowDate()
                logViewModel.fetchLog()
//                TRANSACTION_SCREEN = true
            }
            ScreenTransactionOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.MenuEditOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.MenuEditOwner.route

                STAT_GET_DATA = false


            }
            ScreenMenuEditOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.RulesOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.RulesOwner.route

                STAT_GET_DATA = false

//                TRANSACTION_SCREEN = true
            }
            ScreenRulesOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.UserOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.UserOwner.route

                STAT_GET_DATA = false

//                TRANSACTION_SCREEN = true
            }
            ScreenUserOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.RulesEditOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.RulesEditOwner.route

                STAT_GET_DATA = false
            }
            ScreenRulesEditOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.UserEditOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.UserEditOwner.route

                STAT_GET_DATA = false
            }
            ScreenUserEditOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.OutletOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.OutletOwner.route

                STAT_GET_DATA = false
                PROBLEM_MACHINE_STATE_SCREEN = false

                if(STORE_NAME.isNullOrEmpty()){
                    storeViewModel.GetStore()
                }

                if (LIST_EXPENSES_STORE.isNullOrEmpty() && EXPENSES_STATE_STORE <= 1){
                    expensesViewModel.fetchByStore()
                }

                incomeViewModel.CoroutineFetchIncomeStore()
            }
            ScreenOutletOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.MachineOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.MachineOwner.route

                STAT_GET_DATA = false
                machineViewModel.getMachineList()
            }
            ScreenMachineOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.ExpensesOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.ExpensesOwner.route

                STAT_GET_DATA = false

            }
            ScreenExpensesOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.QrOwner.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.QrOwner.route

            }
            ScreenQrOwner(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.ReportMachine.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.ReportMachine.route

                STAT_GET_DATA = false
                problemViewModel.fetchProblem()
                PROBLEM_MACHINE_STATE_SCREEN = true
            }
            ScreenReportMachine(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.OwnerListDeveloper.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.OwnerListDeveloper.route

                STAT_GET_DATA = false
                userViewModel.fetchOwner()
//                TRANSACTION_SCREEN = true
            }
            ScreenOwnerDeveloper(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }

        composable(
            route = Screens.OwnerEditStoreDeveloper.route,
        ){
            LaunchedEffect(key1 = STORE_ID){

                SCREEN_ACTIVE_NOW = Screens.OwnerEditStoreDeveloper.route

                STAT_GET_DATA = false
            }
            ScreenStoreEditDeveloper(navController = navController, protoViewModel = protoViewModel, bluetoothViewModel = bluetoothViewModel)
        }
    }
}