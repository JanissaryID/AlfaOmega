package com.example.alfaomega.components.store_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    storeAdmin: String,
    storePhone: String,
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
                    INCOME_SUM_STORE = 0
                    EXPENSES_SUM_STORE = 0
                    PROFIT_SUM_STORE = 0
                    INCOME_STATE_STORE = 0

                    STORE_NAME = storeName
                    STORE_CITY = storeCity
                    STORE_ADDRESS = storeAddress
                    STORE_ID = storeId
                    STORE_PHONE = storePhone
                    STORE_ADMIN = storeAdmin

                    protoViewModel.updateStoreID(STORE_ID)

                    if(!STORE_LIST_RESPONSE.isNullOrEmpty() && !STORE_ID.isNullOrEmpty()){
                        navController.navigate(route = Screens.OutletOwner.route){
                            popUpTo(Screens.OutletOwner.route) {
                                inclusive = true
                            }
                        }
                    }
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
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = colorFont
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = storeAddress,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        color = colorFont.copy(alpha = 0.7f),
                        maxLines = 1
//                        lineHeight = 50.sp
//                        style = LocalTextStyle.current.copy(lineHeight = 120.sp)
                    )
                }
            }
        }
    }
}