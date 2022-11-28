package com.example.alfaomega.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ButtonView(title: String, modifier: Modifier = Modifier, enable: Boolean, onClick: () -> Unit ) {

    Button(onClick = { onClick() },
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = null,
        enabled = enable
    ) {
        Text(text = title, fontWeight = FontWeight.SemiBold)
    }
}