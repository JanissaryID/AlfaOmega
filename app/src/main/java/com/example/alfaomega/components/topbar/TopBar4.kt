package com.example.alfaomega.components.topbar

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.api.menu.MenuViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar4(
    tittleScreen: String,
    navController: NavController,
    screenBack: String,
    icon: Int,
    description: String,
    actionNav: String,
    menuViewModel: MenuViewModel = MenuViewModel()
) {
    SmallTopAppBar(
        title = {
            Text(
            text = tittleScreen,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        },
        navigationIcon = {
            Surface(shape = RoundedCornerShape(100), modifier = Modifier.wrapContentSize()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = "Back",
                    modifier = Modifier.clickable {
                        navController.navigate(route = screenBack) {
                            popUpTo(screenBack) {
                                inclusive = true
                            }
                        }
                    },
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        actions = {
            Surface(shape = RoundedCornerShape(100), modifier = Modifier.wrapContentSize()) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = description,
                    modifier = Modifier.clickable {
                        if(USER_TYPE == 0){
                            if(MENU_SCREEN_TYPE && EDIT_MODE){
                                menuViewModel.deleteMenu(navController = navController, idMenu = ID_MENU_EDIT)
                                navController.navigate(route = actionNav) {
                                    popUpTo(actionNav) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                        else{

                        }
                    }
                )
            }
        }
    )
}