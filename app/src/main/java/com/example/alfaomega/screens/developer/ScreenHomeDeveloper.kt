package com.example.alfaomega.screens.developer

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.components.scaffold.Scaffold1
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenHomeDeveloper(navController: NavController, protoViewModel: ProtoViewModel, bluetoothViewModel: BluetoothViewModel) {

    val tittleScreen = stringResource(R.string.DebugTitle)
    val screenBack = Screens.StoreProfile.route
    val floatingRoute = Screens.Menu.route
    val icon = R.drawable.ic_twotone_storefront_24
    val TopBar = 1
    val wallScrreen = 29
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
        route =routeAction,
        bluetoothViewModel = bluetoothViewModel
    )
}