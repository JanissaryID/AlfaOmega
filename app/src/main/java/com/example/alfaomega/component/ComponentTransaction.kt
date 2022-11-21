package com.example.alfaomega.component

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alfaomega.Greeting
import com.example.alfaomega.R
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@Composable
fun ComponentTransaction(
    TransactionId: String,
    TransactionDate: String,
    TransactionMenu: String,
    TransactionType: String,
    TransactionAdmin: String,
    TransactionProcess: String

) {
    Card(
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier.padding(8.dp),
    ) {
        Surface(shape = RoundedCornerShape(7),modifier = Modifier.clickable {  }) {
            Column() {
                Column(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
                ){
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = TransactionId,
//                fontWeight = FontWeight.Light,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        )
                        Text(
                            text = TransactionDate,
//                fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = TransactionMenu,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = TransactionType,
                        fontWeight = FontWeight.Light,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    )
                }
                Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp)
                Column(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_timelapse_24),
                                contentDescription = "Icon TImer",
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
//                            modifier = Modifier.background(color = Color.Transparent),
                                shape = RoundedCornerShape(50)
                            ) {
                                Text(
                                    text = TransactionProcess,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp)
                                )
                            }
                        }
                        Text(
                            text = TransactionAdmin,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AlfaOmegaTheme {
        ComponentTransaction(
            TransactionId = "16283628937356732628",
            TransactionDate = "08-08-2022",
            TransactionMenu = "Cuci-Kering",
            TransactionType = "Giant 8 Kg",
            TransactionAdmin = "Putri Sabila",
            TransactionProcess = "Sedang Mencuci"
        )
    }
}