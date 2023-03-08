package com.example.alfaomega.screens.owner

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.components.scaffold.Scaffold1
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ScreenExpensesOwner(navController: NavController, bluetoothViewModel: BluetoothViewModel, protoViewModel: ProtoViewModel) {

    val tittleScreen = stringResource(R.string.ListExpenses)
    val screenBack = Screens.OutletOwner.route
    val floatingRoute = Screens.Menu.route
    val icon = R.drawable.ic_twotone_today_24
    val TopBar = 4
    val wallScrreen = 27
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