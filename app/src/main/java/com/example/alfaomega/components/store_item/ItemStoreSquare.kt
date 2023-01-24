package com.example.alfaomega.components.store_item

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
import com.example.alfaomega.components.ButtonView
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel

@Composable
fun ItemStoreSquare(
    storeId: String,
    storeName: String,
    storeCity: String,
    storeAddress: String,
    protoViewModel: ProtoViewModel,
    navController: NavController
) {
    val colorFont = MaterialTheme.colorScheme.primary

    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Surface(
            color = Color.Transparent,
            shape = RoundedCornerShape(ROUND_CORNER.dp),
            modifier = Modifier
                .size(180.dp)
                .clickable {
//                if(USER_TYPE == 2){
//                    navController.navigate(route = Screens.Machine.route){
//                        popUpTo(Screens.Machine.route) {
//                            inclusive = true
//                        }
//                    }
//                }
//                else{
//                    TRANSACTION_SCREEN = true
//                    STORE_NAME = storeName
//                    STORE_CITY = storeCity
//                    STORE_ADDRESS = storeAddress
//                    protoViewModel.updateStoreID(keyStore = storeId)
//                    navController.navigate(route = Screens.Home.route){
//                        popUpTo(Screens.Home.route) {
//                            inclusive = true
//                        }
//                    }
//                }
                }) {
            Column() {
                Column(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
                ){
                    Surface(color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(ROUND_CORNER.dp),
                        modifier = Modifier
                            .wrapContentWidth().wrapContentHeight()
                            ) {
                        Text(
                            text = storeCity,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            color = MaterialTheme.colorScheme.background
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = storeName,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = colorFont
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = storeAddress,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = colorFont
                    )
                    Spacer(modifier = Modifier.height(8.dp))




//                    if(USER_TYPE == 2){
//                        Spacer(modifier = Modifier.height(16.dp))
//                        ButtonView(
//                            title = "Edit",
//                            enable = true,
////                            modifier = Modifier.width(90.dp)
//                        ) {
////                            EDIT_MODE = true
////                            USER_NAME_EDIT = name
////                            USER_PASSWORD_EDIT = password
////                            USER_SCREEN_TYPE = true
////                            ID_USER_EDIT = idUser
////                            navController.navigate(route = Screens.UserEditOwner.route){
////                                popUpTo(Screens.UserEditOwner.route) {
////                                    inclusive = true
////                                }
////                            }
//                        }
//                    }
                }
            }
        }
    }
}