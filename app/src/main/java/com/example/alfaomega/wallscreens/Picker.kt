package com.example.alfaomega.wallscreens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.api.transaction.TransactionViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WallPicker(
    wallScreen: Int,
    paddingValues: PaddingValues,
    navController: NavController,
) {
    when(wallScreen){
        0 -> WallHome(paddingValues = paddingValues, navController = navController)
        1 -> WallMenu(paddingValues = paddingValues, navController = navController)
        2 -> WallDetailTransaction(paddingValues = paddingValues, navController = navController, transactionViewModel = TransactionViewModel())
        3 -> WallStore(navController = navController, paddingValues = paddingValues)
        4 -> WallMachine(paddingValues = paddingValues, navController = navController)
        else -> print("Opps tidak ada")
    }
}