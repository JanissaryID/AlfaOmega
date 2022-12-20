package com.example.alfaomega.components.scaffold

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.components.topbar.TopBarPicker
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.wallscreens.WallPicker

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffold1(
    tittleScreen: String,
    wallScreen: Int,
    navController: NavController,
    screenBack: String,
    protoViewModel: ProtoViewModel,
    menuViewModel: MenuViewModel = MenuViewModel(),
    floatingRoute: String,
    TopBar: Int,
    icon: Int,
    description: String,
    route: String
) {
    Scaffold(topBar = {
        TopBarPicker(
            TopBar = TopBar,
            navController = navController,
            tittleScreen = tittleScreen,
            screenBack = screenBack,
            icon = icon,
            description = description,
            route = route,
            protoViewModel = protoViewModel
        )
    },
        content = {
//            WallHome(paddingValues = it)
            WallPicker(
                wallScreen = wallScreen,
                paddingValues = it,
                navController = navController,
                protoViewModel = protoViewModel,
                menuViewModel = menuViewModel
            )
        }
    )
}