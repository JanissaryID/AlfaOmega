package com.example.alfaomega.screens.owner

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.alfaomega.EDIT_MODE
import com.example.alfaomega.R
import com.example.alfaomega.USER_TYPE
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.components.scaffold.Scaffold1
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ScreenUserEditOwner(navController: NavController, protoViewModel: ProtoViewModel, bluetoothViewModel: BluetoothViewModel) {

    val tittleScreen = if(USER_TYPE == 2) if(EDIT_MODE) stringResource(R.string.EditOwnerTitle) else stringResource(
            R.string.CreateOwnerTitle) else if(EDIT_MODE) stringResource(R.string.EditUserTitle) else stringResource(R.string.CreateUserTitle)
    val screenBack = if(USER_TYPE == 2) Screens.OwnerListDeveloper.route else Screens.UserOwner.route
    val floatingRoute = Screens.Home.route
    val icon = R.drawable.ic_twotone_delete_24
    val TopBar = if(EDIT_MODE) 4 else 2
    val wallScrreen = 13
    val desctiptionTopBar = "icon Delete"
    val routeAction = Screens.UserOwner.route
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
        route = routeAction,
        bluetoothViewModel = bluetoothViewModel,
        topBarColor = topBarColor,
        fontTopBar = fontTopBar
    )
}