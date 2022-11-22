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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alfaomega.R
import com.example.alfaomega.component.ComponentTransaction
import com.example.alfaomega.component.TopBar
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@Composable
fun ScreenHome() {
    TopBar(
        typeScreen = true,
        tittleScreen = "Transaction Active",
        wallScreen = 0
    )
}

@Preview(showBackground = true)
@Composable
fun ViewHome() {
    AlfaOmegaTheme {
        ScreenHome()
    }
}