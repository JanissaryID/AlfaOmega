package com.example.alfaomega.components

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

@Composable
fun ComponentTransaction(
    TransactionId: String,
    TransactionDate: String,
    TransactionMenu: String,
    TransactionType: String,
    TransactionCustomer: String,
    TransactionProcess: Int,
    TransactionPrice: String,
    transactionPayment: String,
    transactionAdmin: String,
    navController: NavController
) {
    Card(
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Surface(
            color = Color.Transparent,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.clickable {
                TRANSACTION_SCREEN = false
                TRANSACATION_CUSTOMER = TransactionCustomer
                TRANSACATION_MENU = TransactionMenu
                TRANSACATION_TYPE = TransactionType
                TRANSACATION_PRICE = TransactionPrice
                TRANSACATION_PAYMENT = transactionPayment
                TRANSACATION_DATE = TransactionDate
                TRANSACATION_ADMIN = transactionAdmin
                navController.navigate(route = Screens.DetailTransaction.route)
            }
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
                            text = TransactionId,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        )
                        Text(
                            text = TransactionDate,
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
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_twotone_timelapse_24),
                                contentDescription = "Icon TImer",
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
                                shape = RoundedCornerShape(50),
                            ) {
                                when (TransactionProcess){
                                    0 -> {
                                        Text(
                                            text = "Waiting Wash",
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp),
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    1 -> {
                                        Text(
                                            text = "Washing",
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp),
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    2 -> {
                                        Text(
                                            text = "Finish Wash",
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp),
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    3 -> {
                                        Text(
                                            text = "Waiting Dry",
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp),
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    4 -> {
                                        Text(
                                            text = "Drying",
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp),
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    5 -> {
                                        Text(
                                            text = "Finish Dry",
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp),
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                        Text(
                            text = TransactionCustomer,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
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
//        ComponentTransaction(
//            TransactionId = "16283628937356732628",
//            TransactionDate = "08-08-2022",
//            TransactionMenu = "Cuci-Kering",
//            TransactionClass = "Giant 8 Kg",
//            TransactionCustomer = "Putri Sabila",
//            TransactionProcess = 1,
//            navController = rememberNavController()
//        )
    }
}