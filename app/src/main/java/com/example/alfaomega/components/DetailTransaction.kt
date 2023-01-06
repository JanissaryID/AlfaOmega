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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.whatsapp.WhatsappViewModel
import java.text.NumberFormat
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTransaction(
    transactionViewModel: TransactionViewModel,
    navController: NavController,
    whatsappViewModel: WhatsappViewModel = WhatsappViewModel()
) {
    val selectionProgressMachine = listOf(
        stringResource(R.string.PickWasher),
        stringResource(R.string.WasherProcess),
        stringResource(R.string.SelesaiMencuci),
        stringResource(R.string.PickDryer),
        stringResource(R.string.DryProces),
        stringResource(R.string.FinishDry),
        stringResource(R.string.FinishTransaction)
    )
    var titleButton by remember {
        mutableStateOf("")
    }

    var StatMessage: Int = 0

    var button_clicked by remember { mutableStateOf(false) }
    var text_name by remember {
        if(!TRANSACTION_SCREEN) mutableStateOf(TextFieldValue(TRANSACATION_CUSTOMER))
        else if(!TRANSACATION_PAYMENT.isNullOrEmpty()) mutableStateOf(TextFieldValue(TRANSACATION_CUSTOMER)) else mutableStateOf(TextFieldValue(
            NEW_TRANSACATION_CUSTOMER))
    }
    var text_phone by remember {
        mutableStateOf(TextFieldValue(NEW_TRANSACATION_PHONE))
    }
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

    val paymentMethode = listOf(stringResource(R.string.CashTitle), stringResource(R.string.QrisTitle))

    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.setMaximumFractionDigits(0)

    val context = LocalContext.current
    
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
                        text = stringResource(R.string.CustomerTitle),
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
                            containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            textColor = MaterialTheme.colorScheme.surfaceVariant,
                            disabledTextColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                        readOnly = if(!TRANSACTION_SCREEN) true else false
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if(TRANSACTION_SCREEN){
                        Text(
                            text = stringResource(R.string.PhoneTitle),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            value = text_phone,
                            onValueChange ={
                                text_phone = it
                            },
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                textColor = MaterialTheme.colorScheme.surfaceVariant,
                                disabledTextColor = MaterialTheme.colorScheme.surfaceVariant,
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            readOnly = if(!TRANSACTION_SCREEN) true else false
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.Menu),
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
                                    selected = isSelectedItemMenu(
                                        if (!TRANSACTION_SCREEN) TRANSACATION_MENU
                                        else NEW_TRANSACATION_MENU
                                    ),
                                    onClick = {
                                        onChangeStateMenu(
                                            if (!TRANSACTION_SCREEN) TRANSACATION_MENU
                                            else NEW_TRANSACATION_MENU
                                        )
                                    },
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
                                text = if(!TRANSACTION_SCREEN) TRANSACATION_MENU
                                else NEW_TRANSACATION_MENU,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.TypeMenuTitle),
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
                                    selected = isSelectedItemType(
                                        if (!TRANSACTION_SCREEN) TRANSACATION_TYPE
                                        else NEW_TRANSACATION_TYPE
                                    ),
                                    onClick = {
                                        onChangeStateType(
                                            if (!TRANSACTION_SCREEN) TRANSACATION_TYPE
                                            else NEW_TRANSACATION_TYPE
                                        )
                                    },
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
                            if(!TRANSACTION_SCREEN){
                                Text(
                                    text = TRANSACATION_TYPE,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                )
                            }
                            else {
                                Text(
                                    text = NEW_TRANSACATION_TYPE,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.PriceTitle),
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
                                    selected = isSelectedItemPrice(
                                        if (!TRANSACTION_SCREEN) TRANSACATION_PRICE
                                        else NEW_TRANSACATION_PRICE
                                    ),
                                    onClick = {
                                        onChangeStatePrice(
                                            if (!TRANSACTION_SCREEN) TRANSACATION_PRICE
                                            else NEW_TRANSACATION_PRICE
                                        )
                                    },
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
                            if(!TRANSACTION_SCREEN){
                                Text(
                                    text = "${numberFormat.format(TRANSACATION_PRICE.toInt())}",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                )
                            }
                            else{
                                if(!NEW_TRANSACATION_PRICE.isNullOrEmpty()){
                                    Text(
                                        text = "${numberFormat.format(NEW_TRANSACATION_PRICE.toInt())}",
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.PaymentTitle),
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )
                    if(!TRANSACTION_SCREEN){
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.wrapContentSize(),
                            color = Color.Transparent
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .selectable(
                                        selected = isSelectedItemPayment(TRANSACATION_PAYMENT),
                                        onClick = {
                                            onChangeStatePayment(TRANSACATION_PAYMENT)
                                        },
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
                                    text = TRANSACATION_PAYMENT,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                )
                            }
                        }
                    }
                    else{
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

                    if(!TRANSACTION_SCREEN){
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.DateTransactionTitle),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = TRANSACATION_DATE,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.Admin),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = TRANSACATION_ADMIN,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        )
                    }

                }
            }
        }

        if(!TRANSACTION_SCREEN){
            when (TRANSACATION_STATUS_MACHINE){
                0 -> {
                    NEW_TRANSACATION_BUTTON = true
                    titleButton = selectionProgressMachine[0]
                    StatMessage = 0
                }
                1 -> {
                    NEW_TRANSACATION_BUTTON = false
                    titleButton = selectionProgressMachine[1]
                    StatMessage = 0
                }
                2 -> {
                    NEW_TRANSACATION_BUTTON = true
                    if(TRANSACATION_IS_WASHER && TRANSACATION_IS_DRYER){
                        TRANSACATION_STATUS_MACHINE = 3
                        titleButton = selectionProgressMachine[3]
                        StatMessage = 0
                    }
                    else{
                        TRANSACATION_STATUS_MACHINE = 6
                        titleButton = selectionProgressMachine[6]
                        StatMessage = 1
                    }
                }
                3 -> {
                    NEW_TRANSACATION_BUTTON = true
                    titleButton = selectionProgressMachine[3]
                    StatMessage = 0
                }
                4 -> {
                    NEW_TRANSACATION_BUTTON = false
                    titleButton = selectionProgressMachine[4]
                    StatMessage = 0
                }
                5 -> {
                    NEW_TRANSACATION_BUTTON = true
                    if(TRANSACATION_IS_DRYER){
                        TRANSACATION_STATUS_MACHINE = 6
                        titleButton = selectionProgressMachine[6]
                        StatMessage = 1
                    }
                    else{
                        titleButton = selectionProgressMachine[5]
                        StatMessage = 0
                    }
                }
                6 -> {
                    NEW_TRANSACATION_BUTTON = true
                    titleButton = selectionProgressMachine[6]
                    StatMessage = 1
                }
            }
        }
        else{
            if(!text_name.text.isNullOrEmpty() && !paymentMethode.isEmpty()){
                NEW_TRANSACATION_BUTTON = true
            }
            else{
                NEW_TRANSACATION_BUTTON = false
            }

        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(Button) {
                bottom.linkTo(parent.bottom, 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(modifier = Modifier.weight(1f)) {
                ButtonView(
                    title = if(!TRANSACTION_SCREEN) titleButton else stringResource(R.string.CreateTransactionTitle),
                    enable = NEW_TRANSACATION_BUTTON,
                ) {
                    button_clicked = true

                    if(!TRANSACTION_SCREEN){
                        if(TRANSACATION_STATUS_MACHINE == 6){
                            transactionViewModel.updateTransaction(
                                idTransaction = TRANSACATION_ID,
                                transactionStateMachine = 6,
                                navController = navController
                            )
                        }
                        else{
                            navController.navigate(route = Screens.Machine.route)
                        }
                    }
                    else{
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
                            phoneCustomer = text_phone.text,
                            navController = navController
                        )
                    }
                }
            }
            if(!TRANSACTION_SCREEN){
                Spacer(modifier = Modifier.width(16.dp))
                Surface(modifier = Modifier.weight(1f)) {
                    ButtonView(
                        title = stringResource(R.string.Whatsapp),
                        enable = if(TRANSACTION_NUMBER.isNullOrEmpty()) false else true,
                        colorButton = Color("#25d366".toColorInt()),
                        typeButton = true
                    ) {
                        if(StatMessage == 1){
                            whatsappViewModel.SendMessage(
                                context = context,
                                phone = "+62 ${TRANSACTION_NUMBER.drop(1)}",
                                message = whatsappViewModel.Finish_Message
                            )
                        }
                        else{
                            whatsappViewModel.SendMessage(
                                context = context,
                                phone = "+62 ${TRANSACTION_NUMBER.drop(1)}",
                                message = whatsappViewModel.Send_Message
                            )
                        }
                    }
                }
            }
        }
    }
}