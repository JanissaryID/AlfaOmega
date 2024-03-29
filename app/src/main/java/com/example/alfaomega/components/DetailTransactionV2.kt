package com.example.alfaomega.components

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun DetailTransactionV2(
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
        mutableStateOf(2)
    }

    val paymentMethode = listOf(stringResource(R.string.CashTitle), stringResource(R.string.QrisTitle))

    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.setMaximumFractionDigits(0)

    val context = LocalContext.current

    ConstraintLayout(modifier = Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp, top = 16.dp)) {

        val (CardMenu, Button) = createRefs()

        Spacer(modifier = Modifier.height(16.dp))

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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "$STORE_NAME",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = MaterialTheme.colorScheme.surface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "$TRANSACATION_DATE",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        color = MaterialTheme.colorScheme.surface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "$TRANSACATION_CUSTOMER",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = MaterialTheme.colorScheme.surface
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "( ${TRANSACATION_TYPE} ) ${TRANSACATION_MENU}",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = MaterialTheme.colorScheme.surface
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.PaymentTitle),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Text(
                            text = "$TRANSACATION_PAYMENT",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.PriceTitle),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Text(
                            text = "Rp. ${numberFormat.format(TRANSACATION_PRICE.toInt()).substring(2, numberFormat.format(TRANSACATION_PRICE.toInt()).length)}",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.Nominal),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Text(
                            text = "Rp. ${numberFormat.format(TRANSACTION_MONEY.toInt()).substring(2, numberFormat.format(TRANSACTION_MONEY.toInt()).length)}",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.Change),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Text(
                            text = "Rp. ${numberFormat.format((TRANSACTION_MONEY.toInt() - TRANSACATION_PRICE.toInt())).substring(2, numberFormat.format((TRANSACTION_MONEY.toInt() - TRANSACATION_PRICE.toInt())).length)}",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.Admin),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Text(
                            text = "$TRANSACATION_ADMIN",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                    }

                    Spacer(modifier = Modifier.height(48.dp))

                    Row(modifier = Modifier
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Surface(modifier = Modifier.weight(1f), color = Color.Transparent) {
                            ButtonViewV2(
                                title = if(!TRANSACTION_SCREEN) titleButton else stringResource(R.string.CreateTransactionTitle),
                                enable = NEW_TRANSACATION_BUTTON,
                                colorButton = MaterialTheme.colorScheme.onSecondary
                            ) {
                                button_clicked = true

                                if(TRANSACATION_STATUS_MACHINE == 6 || TRANSACATION_STATUS_MACHINE == 7){
                                    transactionViewModel.updateTransaction(
                                        idTransaction = TRANSACATION_ID,
                                        transactionStateMachine = 6,
                                        navController = navController,
//                                        multiplePermissionState = multiplePermissionState
                                    )
                                }
                                else{
                                    navController.navigate(route = Screens.Machine.route){
                                        popUpTo(Screens.Machine.route) {
                                            inclusive = true
                                        }
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
            if(!text_name.text.isNullOrEmpty() && payment_value_index != 2){
                NEW_TRANSACATION_BUTTON = true
            }
            else{
                NEW_TRANSACATION_BUTTON = false
            }
        }
    }
}