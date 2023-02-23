package com.example.alfaomega.wallscreens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.alfaomega.MACHINE_SCREEN
import com.example.alfaomega.TRANSACTION_SCREEN
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.components.DetailTransaction
import com.example.alfaomega.components.DetailTransactionV2
import com.example.alfaomega.components.DetailTransactionV3

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun WallDetailTransaction(
    paddingValues: PaddingValues,
    navController: NavController,
    transactionViewModel: TransactionViewModel
) {
    MACHINE_SCREEN = false

    Surface(color = Color.Transparent, modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            Spacer(modifier = Modifier.height(16.dp))
            if(!TRANSACTION_SCREEN){
                DetailTransactionV2(
                    transactionViewModel = transactionViewModel,
                    navController = navController
                )
            }
            else{
                DetailTransaction(
                    transactionViewModel = transactionViewModel,
                    navController = navController
                )
            }
        }
    }
}