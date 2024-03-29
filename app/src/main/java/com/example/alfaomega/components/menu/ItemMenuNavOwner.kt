package com.example.alfaomega.components.menu

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.navigations.Screens

@Composable
fun ItemMenuNavOwner(
    iconNav: Int,
    nameIcon: String,
    navMenu: String,
    navController: NavController
) {
    val colorFont = MaterialTheme.colorScheme.primary

    Card(
        modifier = Modifier.padding(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Column(modifier = Modifier
            .wrapContentHeight()
            .width(84.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Surface(color = colorFont,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.wrapContentSize()) {
                Surface(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .size(42.dp)
                        .clickable {
                            if (SCREEN_ACTIVE_NOW == Screens.Home.route && !OWNER_ID.isNullOrEmpty()){
                                navController.navigate(route = navMenu){
                                    popUpTo(navMenu) {
                                        inclusive = true
                                    }
                                }
                            }
                            else if(SCREEN_ACTIVE_NOW == Screens.OutletOwner.route && !STORE_ID.isNullOrEmpty()){

                            }
                            if (!STORE_ID.isNullOrEmpty()){
                                TRANSACTION_STATE = 0
                                MACHINE_STATE = 0

                                TRANSACTION_RESPONSE.clear()
                                LIST_MACHINE_DRYER_GIANT.clear()
                                LIST_MACHINE_DRYER_TITAN.clear()
                                LIST_MACHINE_WASHER_GIANT.clear()
                                LIST_MACHINE_WASHER_TITAN.clear()

                                navController.navigate(route = navMenu){
                                    popUpTo(navMenu) {
                                        inclusive = true
                                    }
                                }
                            }
                        }) {

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            painter = painterResource(id = iconNav),
                            contentDescription = nameIcon,
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.surface
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = nameIcon,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}