package com.example.alfaomega.components

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

@Composable
fun ButtonView(
    title: String, 
    modifier: Modifier = Modifier, 
    colorButton: Color = MaterialTheme.colorScheme.primary, 
    typeButton: Boolean = false,
    enable: Boolean, 
    onClick: () -> Unit) {

    Button(onClick = { onClick() },
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = null,
        colors = ButtonDefaults.buttonColors(containerColor = colorButton),
        enabled = enable
    ) {
        if(!typeButton){
            Text(text = title, fontWeight = FontWeight.SemiBold)
        }
        else{
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_whatsapp),
                    contentDescription = "Icon Whatsapp",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = title, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}