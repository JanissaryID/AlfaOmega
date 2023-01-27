package com.example.alfaomega.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.USER_TYPE
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.components.scaffold.Scaffold1
import com.example.alfaomega.components.scaffold.Scaffold2
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ScreenStoreList(navController: NavController, protoViewModel: ProtoViewModel, bluetoothViewModel: BluetoothViewModel) {

    val tittleScreen = stringResource(R.string.ListStoreTitle)
    val screenBack = if( USER_TYPE == 2 ) Screens.OwnerListDeveloper.route else if(USER_TYPE == 1) Screens.Home.route else Screens.StoreProfile.route
    val floatingRoute = Screens.OwnerEditStoreDeveloper.route
    val icon = R.drawable.ic_twotone_storefront_24
    val TopBar = 2
    val wallScrreen = 5
    val desctiptionTopBar = "icon Store"
    val routeAction = if( USER_TYPE != 3 ) Screens.Home.route else Screens.Store.route
    val topBarColor = MaterialTheme.colorScheme.primary
    val fontTopBar = MaterialTheme.colorScheme.surface

    if(USER_TYPE == 2){
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
    else{
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
}

@Preview(showBackground = true)
@Composable
fun ViewStoreList() {
    AlfaOmegaTheme {
//        ScreenMenu(navController = rememberNavController())
    }
}