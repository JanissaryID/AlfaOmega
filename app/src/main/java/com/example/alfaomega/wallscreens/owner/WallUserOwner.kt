package com.example.alfaomega.wallscreens.owner

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun WallUserOwner(paddingValues: PaddingValues, navController: NavController) {
    Surface(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            StoreLoadData(
//                storeState = STORE_STATE,
//                store = STORE_LIST_RESPONSE,
//                navController = navController,
//                protoViewModel = protoViewModel
//            )
        }
    }
}