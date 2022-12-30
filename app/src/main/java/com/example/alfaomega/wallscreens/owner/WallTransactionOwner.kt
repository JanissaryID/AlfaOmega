package com.example.alfaomega.wallscreens.owner

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.alfaomega.TRANSACTION_RESPONSE
import com.example.alfaomega.TRANSACTION_STATE
import com.example.alfaomega.view.owner.transaction.TransactionListOwnerLoadData

@Composable
fun WallTransactionOwner(
    paddingValues: PaddingValues,
    navController: NavController
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = paddingValues.calculateTopPadding()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TransactionListOwnerLoadData(
            transactionState = TRANSACTION_STATE,
            transactionList = TRANSACTION_RESPONSE,
            navController = navController
        )
    }
}