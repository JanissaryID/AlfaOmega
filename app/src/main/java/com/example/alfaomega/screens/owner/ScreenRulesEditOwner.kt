package com.example.alfaomega.screens.owner

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.alfaomega.EDIT_MODE
import com.example.alfaomega.PROBLEM_MACHINE_STATE_SCREEN
import com.example.alfaomega.R
import com.example.alfaomega.USER_TYPE
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.components.scaffold.Scaffold1
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ScreenRulesEditOwner(navController: NavController, protoViewModel: ProtoViewModel, bluetoothViewModel: BluetoothViewModel) {

    val tittleScreen = if(USER_TYPE == 1 && PROBLEM_MACHINE_STATE_SCREEN) stringResource(R.string.DetailReportTitle)
                        else if(USER_TYPE == 3 && PROBLEM_MACHINE_STATE_SCREEN) stringResource(R.string.CreateProblem)
                        else if(EDIT_MODE) stringResource(R.string.EditRuleTitle)
                        else stringResource(R.string.CreateRuleTitle)
    val screenBack = if(USER_TYPE == 1 && PROBLEM_MACHINE_STATE_SCREEN) Screens.ReportMachine.route else Screens.RulesOwner.route
    val floatingRoute = Screens.Home.route
    val icon = R.drawable.ic_twotone_delete_24
    val TopBar = if(USER_TYPE == 1 && PROBLEM_MACHINE_STATE_SCREEN) 2 else if(EDIT_MODE) 4 else 2
    val wallScrreen = 12
    val desctiptionTopBar = "icon Delete"
    val routeAction = Screens.RulesOwner.route
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