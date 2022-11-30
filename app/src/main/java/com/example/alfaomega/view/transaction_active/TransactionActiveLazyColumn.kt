package com.example.alfaomega.view.transaction_active

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.api.menu.MenuModel
import com.example.alfaomega.api.transaction.TransactionModel
import com.example.alfaomega.components.ComponentMenu
import com.example.alfaomega.components.ComponentTransaction

@Composable
fun TransactionActiveLazyColumn(
    transactionActiveModel: List<TransactionModel>,
    navController: NavController,
//    selectedIndex: Int
) {
    val selectionMenuClass = listOf("Giant 8 Kg", "Titan 12 Kg")

    LazyColumn(
        modifier = Modifier.padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        itemsIndexed(items = transactionActiveModel) { index, transactionActive ->
            ComponentTransaction(
                TransactionId = transactionActive.id.toString(),
                TransactionDate = transactionActive.transactionDate.toString(),
                TransactionMenu = transactionActive.transactionMenu.toString(),
                TransactionClass = if(!transactionActive.transactionClass!!) selectionMenuClass[0] else selectionMenuClass[1],
                TransactionCustomer = transactionActive.transactionCustomer.toString(),
                TransactionProcess = transactionActive.transactionStateMachine!!,
                navController =navController
            )
        }
    }
}