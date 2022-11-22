package com.example.alfaomega.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.alfaomega.component.TopBar
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@Composable
fun ScreenMenu() {
    TopBar(
        typeScreen = false,
        tittleScreen = "New Transaction",
        wallScreen = 1
    )
}

@Preview(showBackground = true)
@Composable
fun ViewMenu() {
    AlfaOmegaTheme {
        ScreenMenu()
    }
}