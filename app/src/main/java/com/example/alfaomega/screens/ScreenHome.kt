package com.example.alfaomega.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alfaomega.component.ComponentTransaction

@Composable
fun ScreenHome() {
    Row(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        ComponentTransaction(
            TransactionId = "16283628937356732628",
            TransactionDate = "08-08-2022",
            TransactionMenu = "Cuci-Kering",
            TransactionType = "Giant 8 Kg",
            TransactionAdmin = "Putri Sabila",
            TransactionProcess = "Sedang Mencuci"
        )
    }
    
}