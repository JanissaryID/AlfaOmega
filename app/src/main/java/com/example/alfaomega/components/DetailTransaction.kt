package com.example.alfaomega.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.api.transaction.TransactionViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTransaction(
    transactionViewModel: TransactionViewModel,
    navController: NavController
) {
    var button_clicked by remember { mutableStateOf(false) }
    var text_name by remember { mutableStateOf(TextFieldValue(NEW_TRANSACATION_CUSTOMER)) }
    var payment_value_index by remember {
        mutableStateOf(0)
    }

    val selectedValueMenu = remember { mutableStateOf("") }
    val isSelectedItemMenu: (String) -> Boolean = { selectedValueMenu.value == it }
    val onChangeStateMenu: (String) -> Unit = { selectedValueMenu.value = it }

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
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Menu",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.wrapContentSize(),
                        color = Color.Transparent
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .selectable(
                                    selected = isSelectedItemMenu(NEW_TRANSACATION_MENU),
                                    onClick = { onChangeStateMenu(NEW_TRANSACATION_MENU) },
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
                                text = NEW_TRANSACATION_MENU,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            )
                        }
                    }
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
                                    selected = isSelectedItemType(NEW_TRANSACATION_TYPE),
                                    onClick = { onChangeStateType(NEW_TRANSACATION_TYPE) },
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
                                text = NEW_TRANSACATION_TYPE,
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
                                    selected = isSelectedItemPrice(NEW_TRANSACATION_PRICE),
                                    onClick = { onChangeStatePrice(NEW_TRANSACATION_PRICE) },
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
                                text = "Rp $NEW_TRANSACATION_PRICE",
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
                    paymentMethode.forEachIndexed { indexItem ,item ->
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
                                        onClick = {
                                            onChangeStatePayment(item)
                                            payment_value_index = indexItem
                                                  },
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

        if(!text_name.text.isNullOrEmpty() && !selectedValuePayment.value.isNullOrEmpty() && !button_clicked){
            NEW_TRANSACATION_BUTTON = true
        }
        else{
            NEW_TRANSACATION_BUTTON = false
        }

        ButtonView(
            title = "Create Transaction",
            enable = NEW_TRANSACATION_BUTTON,
            modifier = Modifier.constrainAs(Button){
                bottom.linkTo(parent.bottom, 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            button_clicked = true
//            Log.i("info_response", "Payment : ${payment_value_index}")
            transactionViewModel.insertTransaction(
                transactionCustomer = text_name.text,
                transactionMenu = NEW_TRANSACATION_MENU,
                transactionPrice = NEW_TRANSACATION_PRICE,
                transactionClass = NEW_TRANSACATION_CLASS,
                transactionPayment = if(payment_value_index == 0) false else true,
                transactionStateMachine = if(NEW_TRANSACATION_IS_WASHER) 0
                                            else if(NEW_TRANSACATION_IS_DRYER) 3
                                            else if (!NEW_TRANSACATION_IS_WASHER && !NEW_TRANSACATION_IS_DRYER) 6
                                            else 0,
                isWasher = NEW_TRANSACATION_IS_WASHER,
                isDryer = NEW_TRANSACATION_IS_DRYER,
                navController = navController
            )
        }
    }
}