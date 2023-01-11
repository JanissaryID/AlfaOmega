package com.example.alfaomega.screens.owner

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.components.scaffold.Scaffold2
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ScreenUserOwner(navController: NavController, protoViewModel: ProtoViewModel, bluetoothViewModel: BluetoothViewModel) {

    val tittleScreen = stringResource(R.string.EmployeeTitle)
    val screenBack = Screens.Home.route
    val floatingRoute = Screens.UserEditOwner.route
    val icon = R.drawable.ic_twotone_storefront_24
    val TopBar = 2
    val wallScrreen = 11
    val desctiptionTopBar = "icon Store"
    val routeAction = Screens.Store.route

    Scaffold2(
        tittleScreen = tittleScreen,
        wallScreen = wallScrreen,
        navController = navController,
        screenBack = screenBack,
        protoViewModel = protoViewModel,
        floatingRoute = floatingRoute,
        TopBar = TopBar,
        icon = icon,
        description = desctiptionTopBar,
        route =routeAction,
        bluetoothViewModel = bluetoothViewModel
    )
}