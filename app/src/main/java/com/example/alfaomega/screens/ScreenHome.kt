package com.example.alfaomega.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.alfaomega.R
import com.example.alfaomega.component.ComponentTransaction
import com.example.alfaomega.component.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenHome() {
    Scaffold(topBar = {
        TopBar(
            typeScreen = true,
            tittleScreen = "Transaction Active"
        ) },
        content = {
            WallHome(paddingValues = it)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.fluent_add_12_filled),
                    contentDescription = "Icon Add",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}

@Composable
fun WallHome(paddingValues: PaddingValues) {
    val numbers = remember {
        mutableStateListOf(1,2,3,4,5,6,7,8,9,10)
    }
    Surface(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        LazyColumn(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(items = numbers, key = {it.hashCode()}) {
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
    }
}