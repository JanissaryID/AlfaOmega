package com.example.alfaomega.components.button_view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.alfaomega.R
import com.example.alfaomega.ROUND_CORNER

@Composable
fun ButtonViewV2(
    title: String,
    modifier: Modifier = Modifier,
    colorButton: Color = MaterialTheme.colorScheme.primary,
    typeButton: Boolean = false,
    enable: Boolean,
    icon: Int = R.drawable.ic_baseline_whatsapp,
    onClick: () -> Unit) {

    Button(onClick = { onClick() },
        modifier = modifier
            .height(62.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(ROUND_CORNER.dp),
        elevation = null,
        colors = ButtonDefaults.buttonColors(containerColor = colorButton),
        enabled = enable
    ) {
        if(!typeButton){
            Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = MaterialTheme.typography.titleMedium.fontSize)
        }
        else{
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Icon Whatsapp",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = MaterialTheme.typography.titleMedium.fontSize)
            }
        }
    }
}