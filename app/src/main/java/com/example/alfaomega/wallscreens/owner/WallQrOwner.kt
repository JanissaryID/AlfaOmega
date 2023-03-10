package com.example.alfaomega.wallscreens.owner

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.api.qr.QrViewModel
import com.example.alfaomega.components.button_view.ButtonViewV2
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallQrOwner(
    navController: NavController,
    paddingValues: PaddingValues,
    qrViewModel: QrViewModel = QrViewModel()
) {
    var button_clicked by remember { mutableStateOf(false) }

    var text_merchant by remember {
        if(!QR_DATA.id.isNullOrEmpty()) mutableStateOf(TextFieldValue(QR_DATA.merchantId!!))
        else mutableStateOf(TextFieldValue(""))
    }

    var text_client by remember {
        if(!QR_DATA.id.isNullOrEmpty()) mutableStateOf(TextFieldValue(QR_DATA.clientId!!))
        else mutableStateOf(TextFieldValue(""))
    }

    var text_key by remember {
        if(!QR_DATA.id.isNullOrEmpty()) mutableStateOf(TextFieldValue(QR_DATA.keyId!!))
        else mutableStateOf(TextFieldValue(""))
    }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding(), start = 16.dp, end = 16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {

            val (CardMenu, Button) = createRefs()

            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
//                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    .constrainAs(CardMenu) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                shape = RoundedCornerShape(ROUND_CORNER.dp)
            ) {
                Column(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ){
                        Text(
                            text = "Merchant ID",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            value = text_merchant,
                            onValueChange ={
                                text_merchant = it
                            },
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                containerColor = MaterialTheme.colorScheme.surface,
                                textColor = MaterialTheme.colorScheme.primary,
                                disabledTextColor = MaterialTheme.colorScheme.surfaceVariant,
                            ),
                            shape = RoundedCornerShape(ROUND_CORNER.dp),
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Client ID",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            value = text_client,
                            onValueChange ={
                                text_client = it
                            },
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                containerColor = MaterialTheme.colorScheme.surface,
                                textColor = MaterialTheme.colorScheme.primary,
                                disabledTextColor = MaterialTheme.colorScheme.surfaceVariant,
                            ),
                            shape = RoundedCornerShape(ROUND_CORNER.dp),
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Key ID",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            value = text_key,
                            onValueChange ={
                                text_key = it
                            },
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                containerColor = MaterialTheme.colorScheme.surface,
                                textColor = MaterialTheme.colorScheme.primary,
                                disabledTextColor = MaterialTheme.colorScheme.surfaceVariant,
                            ),
                            shape = RoundedCornerShape(ROUND_CORNER.dp),
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                        )

                    }
                }
            }

            if(!text_merchant.text.isNullOrEmpty() && !text_client.text.isNullOrEmpty() && !text_key.text.isNullOrEmpty() && !button_clicked) {
                BUTTON_MENU_EDIT = true
            }
            else{
                BUTTON_MENU_EDIT = false
            }

            ButtonViewV2(
                title = if(!QR_DATA.id.isNullOrEmpty()) stringResource(R.string.SaveChanges) else stringResource(
                                    R.string.InsertData),
                enable = BUTTON_MENU_EDIT,
                modifier = Modifier.constrainAs(Button){
                    bottom.linkTo(parent.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                button_clicked = true

                if(!QR_DATA.id.isNullOrEmpty()){
                    qrViewModel.updateQr(
                        idQr = QR_DATA.id!!,
                        merchantID = text_merchant.text,
                        clientID = text_client.text,
                        KeyID = text_key.text,
                        navController = navController
                    )
                }
                else{
                    qrViewModel.insertQR(
                        merchantID = text_merchant.text,
                        clientID = text_client.text,
                        KeyID = text_key.text,
                        ownerID = OWNER_ID,
                        navController = navController
                    )
                }
            }
        }
    }
}