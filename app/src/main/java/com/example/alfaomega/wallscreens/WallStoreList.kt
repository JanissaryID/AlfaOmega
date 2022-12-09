package com.example.alfaomega.wallscreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.components.ComponentMenuClass
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.view.menu.MachineLoadData
import com.example.alfaomega.view.store.StoreLoadData

@Composable
fun WallStoreList(
    paddingValues: PaddingValues,
    navController: NavController,
    protoViewModel: ProtoViewModel
) {
//    val onItemClick = { index: Int -> selectedIndex = index}
    val selectionMenuClass = listOf("Giant", "Titan")
    var selected_index_class by remember { mutableStateOf(MENU_MACHINE_CLASS) }
    val on_click_index_class = { index: Int -> selected_index_class = index}

    Spacer(modifier = Modifier.width(8.dp))
    Surface(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
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
                protoViewModel = protoViewModel
            )
        }
    }
}