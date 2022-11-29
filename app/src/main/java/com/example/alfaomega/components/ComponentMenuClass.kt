package com.example.alfaomega.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ComponentMenuClass(
    titleMenu: String,
    index: Int,
    selected: Boolean = false,
    onClick: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onClick.invoke(index)
        }
    ) {
        Text(
            text = titleMenu,
            fontWeight = FontWeight.ExtraBold,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
        )
        Surface(
            modifier = Modifier.size(width = 70.dp, height = 4.dp),
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(100)
        ) {

        }
    }
}