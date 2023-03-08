package com.example.alfaomega.components.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.alfaomega.ADDRESS_DEVICE
import com.example.alfaomega.ROUND_CORNER
import com.example.alfaomega.TRANSACTION_MONEY
import com.example.alfaomega.proto.ProtoViewModel
import java.text.NumberFormat
import java.util.*

@Composable
fun ItemExpenses(
    nameAdmin: String,
    date: String,
    nominal: String,
    note: String,
) {
    val colorFont = MaterialTheme.colorScheme.primary

    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.setMaximumFractionDigits(0)

    Column() {
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = nameAdmin,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    color = colorFont
                )
                Text(
                    text = date,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    color = colorFont
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Rp. ${numberFormat.format(nominal.toInt()).substring(2, numberFormat.format(nominal.toInt()).length)}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    color = colorFont
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = note,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    color = colorFont
                )
            }
        }
    }
}