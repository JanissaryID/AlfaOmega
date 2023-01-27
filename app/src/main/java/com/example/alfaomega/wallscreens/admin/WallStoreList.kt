package com.example.alfaomega.wallscreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.api.store.StoreViewModel
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.view.admin.store.StoreLoadData

@Composable
fun WallStoreList(
    paddingValues: PaddingValues,
    navController: NavController,
    protoViewModel: ProtoViewModel,
    storeViewModel: StoreViewModel
) {
    Surface(color = Color.Transparent, modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StoreLoadData(
                storeState = STORE_STATE,
                store = STORE_LIST_RESPONSE,
                navController = navController,
                protoViewModel = protoViewModel,
                storeViewModel = storeViewModel
            )
        }
    }
}