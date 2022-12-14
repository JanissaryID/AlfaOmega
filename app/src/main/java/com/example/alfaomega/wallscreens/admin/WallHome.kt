package com.example.alfaomega.wallscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.TRANSACATION_IS_DRYER
import com.example.alfaomega.TRANSACATION_IS_WASHER
import com.example.alfaomega.TRANSACTION_ACTIVE_RESPONSE
import com.example.alfaomega.TRANSACTION_ACTIVE_STATE
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.components.ComponentTransaction
import com.example.alfaomega.view.admin.transaction_active.TransactionActiveLoadData

@Composable
fun WallHome(
    paddingValues: PaddingValues,
    navController: NavController
) {
    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding())
    ) {
        TransactionActiveLoadData(
            transactionState = TRANSACTION_ACTIVE_STATE,
            transactionActive = TRANSACTION_ACTIVE_RESPONSE,
            navController = navController
        )
    }
}