package com.example.alfaomega.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ButtonView(title: String, modifier: Modifier = Modifier, colorButton: Color = MaterialTheme.colorScheme.primary, enable: Boolean, onClick: () -> Unit) {

    Button(onClick = { onClick() },
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = null,
        colors = ButtonDefaults.buttonColors(containerColor = colorButton),
        enabled = enable
    ) {
        Text(text = title, fontWeight = FontWeight.SemiBold)
    }
}