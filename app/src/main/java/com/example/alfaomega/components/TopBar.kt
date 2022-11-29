package com.example.alfaomega.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.wallscreens.WallPicker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    typeScreen: Boolean,
    tittleScreen: String,
    wallScreen: Int,
    navController: NavController,
    screenBack: String
) {
    if (typeScreen){
        Scaffold(topBar = {
            SmallTopBar(
                typeScreen = typeScreen,
                tittleScreen = tittleScreen,
                navController = navController,
                screenBack = screenBack
            )
        },
            content = {
//            WallHome(paddingValues = it)
                WallPicker(
                    wallScreen = wallScreen,
                    paddingValues = it,
                    navController = navController
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate(route = Screens.Menu.route) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.fluent_add_12_filled),
                        contentDescription = "Icon Add",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        )
    }
    else{
        Scaffold(topBar = {
            SmallTopBar(
                typeScreen = typeScreen,
                tittleScreen = tittleScreen,
                navController = navController,
                screenBack = screenBack
            )
        },
            content = {
//            WallHome(paddingValues = it)
                WallPicker(
                    wallScreen = wallScreen,
                    paddingValues = it,
                    navController = navController
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopBar(
    typeScreen: Boolean,
    tittleScreen: String,
    navController: NavController,
    screenBack: String
) {
    if(typeScreen){
        SmallTopAppBar(
            title = {Text(
                text = tittleScreen,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
            )},
            actions = {
                Surface(shape = RoundedCornerShape(100), modifier = Modifier.wrapContentSize()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_storefront_24),
                        contentDescription = "Icon Store",
                        modifier = Modifier.clickable { navController.navigate(route = Screens.StoreProfile.route) }
                    )
                }
            }
        )
    }
    else{
        SmallTopAppBar(
            title = {Text(
                text = tittleScreen,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
            )},
            navigationIcon = {
                Surface(shape = RoundedCornerShape(100), modifier = Modifier.wrapContentSize()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                        contentDescription = "Icon TImer",
                        modifier = Modifier.clickable {
                            navController.navigate(route = screenBack) {
                                popUpTo(screenBack) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            }
        )
    }
}