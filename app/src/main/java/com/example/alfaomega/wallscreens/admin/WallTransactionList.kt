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
import com.example.alfaomega.view.menu.MachineLoadData
import com.example.alfaomega.view.store.StoreLoadData
import com.example.alfaomega.view.transaction_list.TransactionListLoadData

@Composable
fun WallTransactionList(
    paddingValues: PaddingValues,
    navController: NavController,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = paddingValues.calculateTopPadding()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TransactionListLoadData(
            transactionState = TRANSACTION_STATE,
            transactionList = TRANSACTION_RESPONSE,
            navController = navController
        )
    }
}