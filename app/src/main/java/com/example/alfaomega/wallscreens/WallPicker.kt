package com.example.alfaomega.wallscreens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun WallPicker(
    wallScreen: Int,
    paddingValues: PaddingValues,
    navController: NavController
) {
    when(wallScreen){
        0 -> WallHome(paddingValues = paddingValues, navController = navController)
        1 -> WallMenu(paddingValues = paddingValues, navController = navController)
        2 -> WallDetailTransaction(paddingValues = paddingValues)
        else -> print("Opps tidak ada")
    }
}