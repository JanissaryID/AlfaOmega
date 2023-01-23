package com.example.alfaomega.components.topbar

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar1(
    tittleScreen: String,
    containerColor: Color = Color.Transparent,
    colorFont: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    SmallTopAppBar(
        title = {
            Text(
            text = tittleScreen,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = colorFont
        )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = containerColor)
    )
}