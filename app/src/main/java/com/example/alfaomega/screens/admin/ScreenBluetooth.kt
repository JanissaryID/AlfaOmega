package com.example.alfaomega.screens.admin

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.components.scaffold.Scaffold1
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ScreenBluetooth(navController: NavController, protoViewModel: ProtoViewModel, bluetoothViewModel: BluetoothViewModel) {

    val tittleScreen = stringResource(R.string.PrinterTitle)
    val screenBack = Screens.StoreProfile.route
    val floatingRoute = Screens.Menu.route
    val icon = R.drawable.ic_twotone_storefront_24
    val TopBar = 2
    val wallScrreen = 23
    val desctiptionTopBar = "icon Store"
    val routeAction = Screens.Store.route
    val topBarColor = MaterialTheme.colorScheme.primary
    val fontTopBar = MaterialTheme.colorScheme.surface

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
        bluetoothViewModel = bluetoothViewModel,
        topBarColor = topBarColor,
        fontTopBar = fontTopBar
    )
}

@Preview(showBackground = true)
@Composable
fun ViewTransactionList() {
    AlfaOmegaTheme {
//        ScreenMenu(navController = rememberNavController())
    }
}