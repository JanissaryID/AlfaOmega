package com.example.alfaomega.screens.owner

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.components.scaffold.Scaffold1
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ScreenHomeOwner(navController: NavController, protoViewModel: ProtoViewModel, bluetoothViewModel: BluetoothViewModel) {

    val tittleScreen = stringResource(R.string.OwnerTitle)
    val screenBack = Screens.StoreProfile.route
    val floatingRoute = Screens.Menu.route
    val icon = R.drawable.ic_twotone_logout_24
    val TopBar = 3
    val wallScrreen = if(STORE_ID.isNullOrEmpty()) 20 else 8
    val desctiptionTopBar = "icon Store"
    val routeAction = Screens.Home.route

    MENU_SCREEN_TYPE = false
    RULES_SCREEN_TYPE = false
    USER_SCREEN_TYPE = false


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

@Preview(showBackground = true)
@Composable
fun ViewHome() {
    AlfaOmegaTheme {
//        ScreenHome(navController = rememberNavController())
    }
}