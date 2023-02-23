package com.example.alfaomega.view.admin.store

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.api.menu.MenuModel
import com.example.alfaomega.api.store.StoreModel
import com.example.alfaomega.components.ComponentMenu
import com.example.alfaomega.components.ItemStore
import com.example.alfaomega.proto.ProtoViewModel

@Composable
fun StoreLazyColumn(
    storeModel: List<StoreModel>,
    protoViewModel: ProtoViewModel,
    navController: NavController,
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        itemsIndexed(items = storeModel) { index, store ->
            ItemStore(
                storeId = store.id!!,
                storeName = store.name!!,
                storeCity = store.city!!,
                storeAddress = store.address!!,
                protoViewModel = protoViewModel,
                storeAdmin = store.admin!!,
                navController = navController,
            )
        }
    }
}