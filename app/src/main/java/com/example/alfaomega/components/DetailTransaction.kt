package com.example.alfaomega.components

import android.Manifest
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import com.example.alfaomega.bluetoothprinter.PermissionsRequiredState
import com.example.alfaomega.components.button_view.ButtonViewV2
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.whatsapp.WhatsappViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import java.text.NumberFormat
import java.util.*


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun DetailTransaction(
    transactionViewModel: TransactionViewModel,
    navController: NavController,
    whatsappViewModel: WhatsappViewModel = WhatsappViewModel(),
) {

    val multiplePermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_ADVERTISE,
            Manifest.permission.BLUETOOTH_CONNECT
        )
    )

    PermissionsRequiredState(multiplePermissionState = multiplePermissionState)

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

    val messageToast = stringResource(R.string.Gratherthand)

    var StatMessage: Int = 0

    var button_clicked by remember { mutableStateOf(false) }
    var text_name by remember {
        if(!TRANSACTION_SCREEN) mutableStateOf(TextFieldValue(TRANSACATION_CUSTOMER))
        else if(!TRANSACATION_PAYMENT.isNullOrEmpty()) mutableStateOf(TextFieldValue(TRANSACATION_CUSTOMER)) else mutableStateOf(TextFieldValue(
            NEW_TRANSACATION_CUSTOMER))
    }
    var text_phone by remember {
        mutableStateOf(NEW_TRANSACATION_PHONE)
    }
    var payment_value_index by remember {
        mutableStateOf(0)
    }
    var text_money by remember {
        mutableStateOf("")
    }

    val selectedValuePayment = remember { mutableStateOf(0) }
    val isSelectedItemPayment: (Int) -> Boolean = { selectedValuePayment.value == it }
    val onChangeStatePayment: (Int) -> Unit = { selectedValuePayment.value = it }

    val paymentMethode = listOf(stringResource(R.string.CashTitle), stringResource(R.string.QrisTitle))

    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.setMaximumFractionDigits(0)

    val context = LocalContext.current

    if(TRANSACTION_LOADING){
        Box(
            modifier = Modifier.fillMaxSize().background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    
    ConstraintLayout(modifier = Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp, top = 16.dp)) {

        val (CardMenu, Button) = createRefs()

        Card(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .constrainAs(CardMenu) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(ROUND_CORNER.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ){

                    Text(
                        text = if(!TRANSACTION_SCREEN) "( $TRANSACATION_TYPE ) $TRANSACATION_MENU"
                        else "( $NEW_TRANSACATION_TYPE ) $NEW_TRANSACATION_MENU",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.PriceTitle),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
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

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                    Spacer(modifier = Modifier.height(16.dp))

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
                                text_phone = it.filter { it.isDigit() }
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
                        text = stringResource(R.string.PaymentTitle),
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
                                        selected = isSelectedItemPayment(indexItem),
                                        onClick = {
                                            onChangeStatePayment(indexItem)
                                            payment_value_index = indexItem
                                        },
                                        role = Role.RadioButton
                                    )
                                    .padding(8.dp)
                            ) {
                                RadioButton(
                                    selected = isSelectedItemPayment(indexItem),
                                    onClick = null,
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
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
                    if(selectedValuePayment.value == 0){
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.Money),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            value = text_money,
                            onValueChange ={
                                text_money = it.filter { it.isDigit() }
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
                            readOnly = false
                        )
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
                7 -> {
                    NEW_TRANSACATION_BUTTON = true
                    titleButton = selectionProgressMachine[6]
                    StatMessage = 1
                }
            }
        }
        else{
            if(!text_name.text.isNullOrEmpty() &&
                if(selectedValuePayment.value == 0) !text_money.isNullOrEmpty() else true
//                        && (text_money.text.toInt() >= TRANSACATION_PRICE.toInt())
            ){
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
            Surface(modifier = Modifier.weight(1f), color = Color.Transparent) {
                ButtonViewV2(
                    title = if(!TRANSACTION_SCREEN) titleButton else stringResource(R.string.CreateTransactionTitle),
                    enable = NEW_TRANSACATION_BUTTON,
                ) {
                    button_clicked = true
                    TRANSACTION_LOADING = true

                    Log.d("get_log", "Loading : $TRANSACTION_LOADING")

                    if(!TRANSACTION_SCREEN){
                        if(TRANSACATION_STATUS_MACHINE == 6 || TRANSACATION_STATUS_MACHINE == 7){
                            transactionViewModel.updateTransaction(
                                idTransaction = TRANSACATION_ID,
                                transactionStateMachine = 6,
                                navController = navController,
//                                multiplePermissionState = multiplePermissionState
                            )
                        }
                        else{
                            navController.navigate(route = Screens.Machine.route)
                        }
                    }
                    else{
                        if(selectedValuePayment.value == 1){
                            transactionViewModel.insertTransaction(
                                transactionCustomer = text_name.text,
                                transactionMenu = NEW_TRANSACATION_MENU,
                                transactionPrice = NEW_TRANSACATION_PRICE,
                                transactionClass = NEW_TRANSACATION_CLASS,
                                transactionPayment = if(payment_value_index == 0) false else true,
                                transactionStateMachine = if(NEW_TRANSACATION_IS_WASHER) 0
                                else if(NEW_TRANSACATION_IS_DRYER) 3
                                else if (!NEW_TRANSACATION_IS_WASHER && !NEW_TRANSACATION_IS_DRYER) 7
                                else 0,
                                isWasher = NEW_TRANSACATION_IS_WASHER,
                                isDryer = NEW_TRANSACATION_IS_DRYER,
                                phoneCustomer = text_phone,
                                userMoney = if(selectedValuePayment.value == 0) text_money else NEW_TRANSACATION_PRICE,
                                navController = navController
                            )
                        }
                        else if(selectedValuePayment.value == 0 && text_money.toInt() >= NEW_TRANSACATION_PRICE.toInt()){
                            transactionViewModel.insertTransaction(
                                transactionCustomer = text_name.text,
                                transactionMenu = NEW_TRANSACATION_MENU,
                                transactionPrice = NEW_TRANSACATION_PRICE,
                                transactionClass = NEW_TRANSACATION_CLASS,
                                transactionPayment = if(payment_value_index == 0) false else true,
                                transactionStateMachine = if(NEW_TRANSACATION_IS_WASHER) 0
                                else if(NEW_TRANSACATION_IS_DRYER) 3
                                else if (!NEW_TRANSACATION_IS_WASHER && !NEW_TRANSACATION_IS_DRYER) 7
                                else 0,
                                isWasher = NEW_TRANSACATION_IS_WASHER,
                                isDryer = NEW_TRANSACATION_IS_DRYER,
                                phoneCustomer = text_phone,
                                userMoney = if(selectedValuePayment.value == 0) text_money else NEW_TRANSACATION_PRICE,
                                navController = navController
                            )
                        }
                        else{
                            button_clicked = false
                            Toast.makeText(context, "$messageToast" , Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            if(!TRANSACTION_SCREEN){
                Spacer(modifier = Modifier.width(16.dp))
                Surface(modifier = Modifier.weight(1f), color = Color.Transparent) {
                    ButtonViewV2(
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