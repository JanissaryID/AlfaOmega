package com.example.alfaomega.view.owner.store

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.api.store.StoreModel
import com.example.alfaomega.components.store_item.ItemStoreSquare
import com.example.alfaomega.proto.ProtoViewModel

@Composable
fun StoreLazyCRow(
    storeModel: List<StoreModel>,
    protoViewModel: ProtoViewModel,
    navController: NavController,
) {
    LazyRow(
        modifier = Modifier
            .padding(top = 8.dp)
            .wrapContentHeight().fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        itemsIndexed(items = storeModel) { index, store ->
            ItemStoreSquare(
                storeId = store.id!!,
                storeName = store.name!!,
                storeCity = store.city!!,
                storeAddress = store.address!!,
                protoViewModel = protoViewModel,
                navController = navController,
            )
        }
    }
}