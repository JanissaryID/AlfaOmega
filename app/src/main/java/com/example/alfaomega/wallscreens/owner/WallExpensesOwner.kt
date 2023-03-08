package com.example.alfaomega.wallscreens.owner

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.view.admin.expenses.ExpensesLoadData
import com.example.alfaomega.view.owner.transaction.TransactionListOwnerLoadData

@Composable
fun WallExpensesOwner(
    navController: NavController,
    paddingValues: PaddingValues,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = paddingValues.calculateTopPadding()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExpensesLoadData(
            expenses = LIST_EXPENSES_STORE,
            expensesState = EXPENSES_STATE_STORE,
            navController = navController,

        )
    }
}