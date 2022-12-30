package com.example.alfaomega.components.topbar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.api.user.UserViewModel
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.wallscreens.*
import com.example.alfaomega.wallscreens.developer.WallHomeDeveloper
import com.example.alfaomega.wallscreens.owner.WallHomeOwner
import com.example.alfaomega.wallscreens.owner.WallMenuEditOwner

@RequiresApi(Build.VERSION_CODES.O)
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
) {
    when(TopBar){
        1 -> TopBar1(tittleScreen = tittleScreen)
        2 -> TopBar2(tittleScreen = tittleScreen, navController = navController, screenBack = screenBack)
        3 -> TopBar3(tittleScreen = tittleScreen, navController = navController, route = route, icon = icon, description = description, protoViewModel = protoViewModel)
        4 -> TopBar4(tittleScreen = tittleScreen, navController = navController, screenBack = screenBack, icon = icon, description = description, actionNav = route)
        5 -> TopBar5(tittleScreen = tittleScreen, navController = navController, screenBack = screenBack, icon = icon, description = description, actionNav = route)
        else -> print("Opps tidak ada")
    }
}