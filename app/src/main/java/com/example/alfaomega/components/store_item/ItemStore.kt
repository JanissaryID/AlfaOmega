package com.example.alfaomega.components

import android.app.Application
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
import com.example.alfaomega.api.store.StoreViewModel
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel

@Composable
fun ItemStore(
    storeId: String,
    storeName: String,
    storeCity: String,
    storeAddress: String,
    storeAdmin: String,
    protoViewModel: ProtoViewModel,
    storeViewModel: StoreViewModel = StoreViewModel(),
    navController: NavController
) {
    Card(
//        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if(storeAdmin.isNullOrEmpty()) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Surface(
            color = Color.Transparent,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.clickable {
                if(storeAdmin.isNullOrEmpty()){
                    if(USER_TYPE == 2){
                        navController.navigate(route = Screens.Machine.route){
                            popUpTo(Screens.Machine.route) {
                                inclusive = true
                            }
                        }
                    }
                    else{
//                        TRANSACTION_SCREEN = true

                        if(!STORE_ID.isNullOrEmpty()){
                            storeViewModel.updateStoreAdmin(
                                admin = "",
                                storeID = STORE_ID,
                                navController = navController
                            )
                        }

                        STORE_NAME = storeName
                        STORE_CITY = storeCity
                        STORE_ADDRESS = storeAddress
                        protoViewModel.updateStoreID(keyStore = storeId)
                        storeViewModel.updateStoreAdmin(
                            admin = USER_NAME,
                            storeID = storeId,
                            navController = navController
                        )
//                    navController.navigate(route = Screens.Home.route){
//                        popUpTo(Screens.Home.route) {
//                            inclusive = true
//                        }
//                    }
                    }
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
                            text = storeName,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = MaterialTheme.typography.labelLarge.fontSize,
                        )
                        Text(
                            text = storeCity,
                            fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = storeAddress,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        )
                    }
                    if(USER_TYPE == 2){
                        Spacer(modifier = Modifier.height(16.dp))
                        ButtonView(
                            title = "Edit",
                            enable = true,
//                            modifier = Modifier.width(90.dp)
                        ) {
//                            EDIT_MODE = true
//                            USER_NAME_EDIT = name
//                            USER_PASSWORD_EDIT = password
//                            USER_SCREEN_TYPE = true
//                            ID_USER_EDIT = idUser
//                            navController.navigate(route = Screens.UserEditOwner.route){
//                                popUpTo(Screens.UserEditOwner.route) {
//                                    inclusive = true
//                                }
//                            }
                        }
                    }
                }
            }
        }
    }
}