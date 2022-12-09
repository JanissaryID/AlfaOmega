package com.example.alfaomega.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ItemListTransaction(
    transactionName: String,
    transactionType: String,
    transactionDate: String,
    transactionPrice: String
) {
    Column() {
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = transactionName,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = transactionDate,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = transactionType,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "IDR ${transactionPrice}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}