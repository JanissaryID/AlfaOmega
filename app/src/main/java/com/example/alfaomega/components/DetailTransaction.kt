package com.example.alfaomega.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.alfaomega.NAME_CUSTOMER_NEW_TRANSACATION
import com.example.alfaomega.PRICE_NEW_TRANSACATION
import com.example.alfaomega.TITLE_NEW_TRANSACATION
import com.example.alfaomega.TYPE_NEW_TRANSACATION

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTransaction(
    transcationTitle: String,
    transcationType: String,
    transactionProcess: String,
    transactionAdmin: String
) {
    var text_name by remember { mutableStateOf(TextFieldValue(NAME_CUSTOMER_NEW_TRANSACATION.toString())) }
    var text_menu by remember { mutableStateOf(TextFieldValue(TITLE_NEW_TRANSACATION.toString())) }

    val selectedValueType = remember { mutableStateOf("") }
    val isSelectedItemType: (String) -> Boolean = { selectedValueType.value == it }
    val onChangeStateType: (String) -> Unit = { selectedValueType.value = it }

    val selectedValuePrice = remember { mutableStateOf("") }
    val isSelectedItemPrice: (String) -> Boolean = { selectedValuePrice.value == it }
    val onChangeStatePrice: (String) -> Unit = { selectedValuePrice.value = it }

    val selectedValuePayment = remember { mutableStateOf("") }
    val isSelectedItemPayment: (String) -> Boolean = { selectedValuePayment.value == it }
    val onChangeStatePayment: (String) -> Unit = { selectedValuePayment.value = it }

    val paymentMethode = listOf("Cash", "Qris")
    
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (CardMenu, Button) = createRefs()

        Card(
            elevation = CardDefaults.cardElevation(6.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .constrainAs(CardMenu) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ){
                    Text(
                        text = "Name",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    TextField(
                        value = text_name,
                        onValueChange ={
                            text_name = it
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
//                            backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Menu",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    TextField(
                        value = text_menu,
                        onValueChange ={
                            text_menu = it
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
//                            backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Type Menu",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.wrapContentSize(),
                        color = Color.Transparent
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .selectable(
                                    selected = isSelectedItemType(TYPE_NEW_TRANSACATION),
                                    onClick = { onChangeStateType(TYPE_NEW_TRANSACATION) },
                                    role = Role.RadioButton
                                )
                                .padding(8.dp)
                        ) {
                            RadioButton(
                                selected = true,
                                onClick = null,
                                enabled = false
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = TYPE_NEW_TRANSACATION,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Price",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.wrapContentSize(),
                        color = Color.Transparent
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .selectable(
                                    selected = isSelectedItemPrice(PRICE_NEW_TRANSACATION),
                                    onClick = { onChangeStatePrice(PRICE_NEW_TRANSACATION) },
                                    role = Role.RadioButton
                                )
                                .padding(8.dp)
                        ) {
                            RadioButton(
                                selected = true,
                                onClick = null,
                                enabled = false
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = PRICE_NEW_TRANSACATION,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Payment",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )
                    paymentMethode.forEach { item ->
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.wrapContentSize(),
                            color = Color.Transparent
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .selectable(
                                        selected = isSelectedItemPayment(item),
                                        onClick = { onChangeStatePayment(item) },
                                        role = Role.RadioButton
                                    )
                                    .padding(8.dp)
                            ) {
                                RadioButton(
                                    selected = isSelectedItemPayment(item),
                                    onClick = null
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(
                                    text = item,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                )
                            }
                        }
                    }
                }
            }
        }

        ButtonView(
            title = "Print Bill",
            enable = true,
            modifier = Modifier.constrainAs(Button){
                bottom.linkTo(parent.bottom, 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
        }
    }
}