package com.example.alfaomega.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.navigations.Screens

@Composable
fun ComponentMenu(
    menuTitle: String,
    menuType: String,
    menuPrice: String,
    menuTime: Int,
    isWasher: Boolean,
    isDryer: Boolean,
    isService: Boolean,
    menuClass: Boolean,
    idmenu: String,
    navController: NavController
) {
    Card(
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Surface(
            color = Color.Transparent,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.clickable {

                if(USER_TYPE == 0){
                    EDIT_MODE = true
                    TITLE_MENU_EDIT = menuTitle
                    PRICE_MENU_EDIT = menuPrice
                    WASHER_MENU_EDIT = isWasher
                    DRYER_MENU_EDIT = isDryer
                    SERVICE_MENU_EDIT = isService
                    CLASS_MENU_EDIT_STRING = if(menuClass) "Titan" else "Giant"
                    ID_MENU_EDIT = idmenu
                    MENU_SCREEN_TYPE = true
                    navController.navigate(route = Screens.MenuEditOwner.route)
                }
                else{
                    TRANSACTION_SCREEN = true
                    NEW_TRANSACATION_MENU = menuTitle
                    NEW_TRANSACATION_PRICE = menuPrice
                    NEW_TRANSACATION_TIME = menuTime
                    NEW_TRANSACATION_TYPE = menuType
                    NEW_TRANSACATION_IS_WASHER = isWasher
                    NEW_TRANSACATION_IS_DRYER = isDryer
                    NEW_TRANSACATION_CLASS = menuClass
                    navController.navigate(route = Screens.DetailTransaction.route)
                }
            }) {
            Column() {
                Column(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
                ){
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = menuTitle,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = MaterialTheme.typography.labelLarge.fontSize,
                        )
                        Text(
                            text = menuPrice,
                            fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = menuType,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        )
//                        Text(
//                            text = "$menuTime Menit",
//                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
//                        )
                    }
                }
            }
        }
    }
}