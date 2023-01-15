package com.example.alfaomega.components.topbar

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.alfaomega.MY_CONTEXT
import com.example.alfaomega.R
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.api.user.UserViewModel
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.bluetoothprinter.PermissionsRequiredState
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.wallscreens.*
import com.example.alfaomega.wallscreens.developer.WallHomeDeveloper
import com.example.alfaomega.wallscreens.owner.WallHomeOwner
import com.example.alfaomega.wallscreens.owner.WallMenuEditOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun TopBarPicker(
    TopBar: Int,
    tittleScreen:  String = "Home",
    navController: NavController,
    screenBack: String = Screens.Home.route,
    route: String = Screens.Home.route,
    icon: Int = R.drawable.ic_twotone_storefront_24,
    description: String = "Description",
    protoViewModel: ProtoViewModel,
    bluetoothViewModel: BluetoothViewModel
) {

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

    PermissionsRequiredState(multiplePermissionState = multiplePermissionState)

    when(TopBar){
        1 -> TopBar1(tittleScreen = tittleScreen)
        2 -> TopBar2(tittleScreen = tittleScreen, navController = navController, screenBack = screenBack)
        3 -> TopBar3(tittleScreen = tittleScreen, navController = navController, route = route, icon = icon, description = description,
            protoViewModel = protoViewModel, multiplePermissionState = multiplePermissionState, context = MY_CONTEXT!!, bluetoothViewModel = bluetoothViewModel)
        4 -> TopBar4(tittleScreen = tittleScreen, navController = navController, screenBack = screenBack, icon = icon, description = description, actionNav = route, multiplePermissionState = multiplePermissionState, bluetoothViewModel = bluetoothViewModel)
        5 -> TopBar5(tittleScreen = tittleScreen, navController = navController, screenBack = screenBack, icon = icon, description = description)
        else -> print("Opps tidak ada")
    }
}