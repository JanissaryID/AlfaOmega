package com.example.alfaomega.wallscreens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

@Composable
fun WallPicker(
    wallScreen: Int,
    paddingValues: PaddingValues
) {
    when(wallScreen){
        0 -> WallHome(paddingValues = paddingValues)
        1 -> WallMenu(paddingValues = paddingValues)
        2 -> WallDetailTransaction(paddingValues = paddingValues)
        else -> print("Opps tidak ada")
    }
}