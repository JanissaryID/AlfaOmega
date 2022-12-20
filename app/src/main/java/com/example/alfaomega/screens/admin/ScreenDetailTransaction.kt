package com.example.alfaomega.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.TRANSACTION_SCREEN
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.components.scaffold.Scaffold1
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenDetailTransaction(
    navController: NavController,
    protoViewModel: ProtoViewModel,
) {

    val tittleScreen =if(!TRANSACTION_SCREEN)
        stringResource(R.string.DetailTransactionTitle) else
            stringResource(R.string.CreateTransactionTitle)
    val screenBack = if (!TRANSACTION_SCREEN) Screens.Home.route else Screens.Menu.route
    val floatingRoute = Screens.Menu.route
    val icon = R.drawable.ic_twotone_print_24
    val TopBar = if (!TRANSACTION_SCREEN) 4 else 2
    val wallScrreen = 2
    val desctiptionTopBar = "icon Print Nota"
    val routeAction = Screens.Home.route

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

@Preview(showBackground = true)
@Composable
fun ViewDetailTransaction() {
    AlfaOmegaTheme {
//        ScreenDetailTransaction(navController = rememberNavController())
    }
}