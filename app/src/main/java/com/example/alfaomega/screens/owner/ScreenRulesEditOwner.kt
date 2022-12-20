package com.example.alfaomega.screens.owner

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.alfaomega.EDIT_MODE
import com.example.alfaomega.R
import com.example.alfaomega.components.scaffold.Scaffold1
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenRulesEditOwner(navController: NavController, protoViewModel: ProtoViewModel) {

    val tittleScreen = if(EDIT_MODE) stringResource(R.string.EditRuleTitle) else stringResource(R.string.CreateRuleTitle)
    val screenBack = Screens.RulesOwner.route
    val floatingRoute = Screens.Home.route
    val icon = R.drawable.ic_twotone_delete_24
    val TopBar = if(EDIT_MODE) 4 else 2
    val wallScrreen = 12
    val desctiptionTopBar = "icon Delete"
    val routeAction = Screens.RulesOwner.route

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
        route = routeAction
    )
}