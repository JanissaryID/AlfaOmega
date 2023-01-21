package com.example.alfaomega.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.TRANSACATION_STATUS_MACHINE
import com.example.alfaomega.USER_TYPE
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.components.scaffold.Scaffold1
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ScreenMachine(navController: NavController, protoViewModel: ProtoViewModel, bluetoothViewModel: BluetoothViewModel) {

    val tittleScreen = if(USER_TYPE == 2) stringResource(R.string.MachineTitle) else if(TRANSACATION_STATUS_MACHINE == 0)
        stringResource(R.string.WashmachineTitle) else
        stringResource(R.string.DryMachineTitle)
    val screenBack = if(USER_TYPE == 2) Screens.OwnerListDeveloper.route else Screens.DetailTransaction.route
    val floatingRoute = Screens.Menu.route
    val icon = R.drawable.ic_twotone_storefront_24
    val TopBar = 2
    val wallScrreen = 4
    val desctiptionTopBar = "icon Store"
    val routeAction = Screens.Store.route

    Scaffold1(
        tittleScreen = tittleScreen,
        wallScreen = wallScrreen,
        navController = navController,
        screenBack = screenBack,
        protoViewModel = protoViewModel,
        floatingRoute = floatingRoute,
        TopBar = TopBar,
        icon = icon,
        description = desctiptionTopBar,
        route = routeAction,
        bluetoothViewModel = bluetoothViewModel
    )
}

@Preview(showBackground = true)
@Composable
fun ViewMachine() {
    AlfaOmegaTheme {
//        ScreenMachine(navController = rememberNavController())
    }
}