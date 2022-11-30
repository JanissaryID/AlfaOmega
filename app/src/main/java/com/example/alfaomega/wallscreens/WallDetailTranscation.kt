package com.example.alfaomega.wallscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.components.DetailTransaction

@Composable
fun WallDetailTransaction(
    paddingValues: PaddingValues,
    navController: NavController,
    transactionViewModel: TransactionViewModel
) {
    Surface(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            DetailTransaction(
//                transcationTitle = "Cuci-Kering",
//                transcationType = "Giant 8 Kg",
//                transactionProcess = "Proses Cuci",
//                transactionAdmin = "Putri Sabila",
                transactionViewModel = transactionViewModel,
                navController = navController
            )
        }
    }
}