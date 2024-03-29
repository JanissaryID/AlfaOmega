package com.example.alfaomega.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.STORE_ID
import com.example.alfaomega.USER_TYPE
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.components.scaffold.Scaffold1
import com.example.alfaomega.components.scaffold.Scaffold2
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ScreenHome(navController: NavController, protoViewModel: ProtoViewModel, bluetoothViewModel: BluetoothViewModel) {

    val tittleScreen =if(USER_TYPE == 0) "" else if(STORE_ID.isNullOrEmpty()) stringResource(R.string.HelloTItle) else  stringResource(R.string.TransactionActiveTitle)
    val screenBack = if( USER_TYPE == 1 ) Screens.Home.route else Screens.StoreProfile.route
    val floatingRoute = Screens.Menu.route
    val icon = R.drawable.ic_twotone_storefront_24
    val TopBar = if( STORE_ID.isNullOrEmpty() ) 1 else 3
    val wallScrreen = if(USER_TYPE == 0) 7 else if(STORE_ID.isNullOrEmpty()) 5 else 0
    val desctiptionTopBar = "icon Store"
    val routeAction = Screens.StoreProfile.route
    val topBarColor = MaterialTheme.colorScheme.primary
    val fontTopBar = MaterialTheme.colorScheme.surface
//
    if(USER_TYPE != 0)
    {
        if(STORE_ID.isNullOrEmpty()){
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
        else{
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
                bluetoothViewModel = bluetoothViewModel,
                topBarColor = topBarColor,
                fontTopBar = fontTopBar
            )
        }
//        Scaffold2(
//            tittleScreen = tittleScreen,
//            wallScreen = wallScrreen,
//            navController = navController,
//            screenBack = screenBack,
//            protoViewModel = protoViewModel,
//            floatingRoute = floatingRoute,
//            TopBar = TopBar,
//            icon = icon,
//            description = desctiptionTopBar,
//            route =routeAction,
//            bluetoothViewModel = bluetoothViewModel,
//            topBarColor = topBarColor,
//            fontTopBar = fontTopBar
//        )
    }
    else
    {
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
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ViewHome() {
    AlfaOmegaTheme {
//        ScreenHome(navController = rememberNavController())
    }
}