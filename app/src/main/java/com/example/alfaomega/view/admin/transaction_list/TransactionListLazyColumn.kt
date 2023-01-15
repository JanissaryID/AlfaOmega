package com.example.alfaomega.view.admin.transaction_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.api.transaction.TransactionModel
import com.example.alfaomega.components.ComponentTransaction
import com.example.alfaomega.components.ItemListTransaction

@Composable
fun TransactionListLazyColumn(
    transactionListModel: List<TransactionModel>,
    navController: NavController,
) {
    val selectionMenuClass = listOf(stringResource(R.string.MenuGiant), stringResource(R.string.MenuTitan))

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        itemsIndexed(items = transactionListModel) { index, transactionList ->
            ItemListTransaction(
                transactionName = transactionList.transactionMenu!!,
                transactionType = if(!transactionList.transactionClass!!) selectionMenuClass[0] else selectionMenuClass[1],
                transactionDate = transactionList.transactionDate!!,
                transactionPrice = transactionList.transactionPrice!!
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
        }
    }
}