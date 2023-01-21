package com.example.alfaomega.wallscreens.owner

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.components.ButtonView

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallMenuEditOwner(
    paddingValues: PaddingValues,
    menuViewModel: MenuViewModel,
    navController: NavController
) {
    var button_clicked by remember { mutableStateOf(false) }
    var text_name_menu by remember {
        if(EDIT_MODE) mutableStateOf(TextFieldValue(TITLE_MENU_EDIT))
        else mutableStateOf(TextFieldValue(""))
    }
    var text_price_menu by remember {
        if(EDIT_MODE) mutableStateOf(TextFieldValue(PRICE_MENU_EDIT))
        else mutableStateOf(TextFieldValue(""))
    }
    var class_value_index by remember {
        mutableStateOf(0)
    }

    val selectedValueType = remember {
        if(EDIT_MODE) mutableStateOf(CLASS_MENU_EDIT_STRING)
        else mutableStateOf("")
    }
    val isSelectedItemType: (String) -> Boolean = { selectedValueType.value == it }
    val onChangeStateType: (String) -> Unit = { selectedValueType.value = it }

    val classMachine = listOf(stringResource(R.string.MenuGiant), stringResource(R.string.MenuTitan))

    val checkedStateWasher = remember {
        if(EDIT_MODE) mutableStateOf(WASHER_MENU_EDIT)
        else mutableStateOf(false)
    }
    val checkedStateDryer = remember {
        if(EDIT_MODE) mutableStateOf(DRYER_MENU_EDIT)
        else mutableStateOf(false)
    }
    val checkedStateService = remember {
        if(EDIT_MODE) mutableStateOf(SERVICE_MENU_EDIT)
        else mutableStateOf(false)
    }

    Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding(), start = 16.dp, end = 16.dp)) {
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
                            text = stringResource(R.string.TitleMenuEdit),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            value = text_name_menu,
                            onValueChange ={
                                text_name_menu = it
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
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.PriceTitle),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            value = text_price_menu,
                            onValueChange ={
                                text_price_menu = it
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
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.ClassMenu),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        )
                        classMachine.forEachIndexed { indexItem ,item ->
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.wrapContentSize(),
                                color = Color.Transparent
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .selectable(
                                            selected = isSelectedItemType(item),
                                            onClick = {
                                                onChangeStateType(item)
                                                class_value_index = indexItem
                                            },
                                            role = Role.RadioButton
                                        )
                                        .padding(8.dp)
                                ) {
                                    RadioButton(
                                        selected = isSelectedItemType(item),
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
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.TypeMenuTitle),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = checkedStateWasher.value,
                                onCheckedChange = { checkedStateWasher.value = it }
                            )
                            Text(
                                text = stringResource(R.string.WasherTitle),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = checkedStateDryer.value,
                                onCheckedChange = { checkedStateDryer.value = it }
                            )
                            Text(
                                text = stringResource(R.string.DryerTitle),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = checkedStateService.value,
                                onCheckedChange = { checkedStateService.value = it }
                            )
                            Text(
                                text = stringResource(R.string.ServiceTitle),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            )
                        }
                    }
                }
            }

            if(
                !text_price_menu.text.isNullOrEmpty() &&
                !text_name_menu.text.isNullOrEmpty() &&
                !selectedValueType.value.isNullOrEmpty() &&
                (checkedStateWasher.value || checkedStateDryer.value || checkedStateService.value) &&
                !button_clicked
            ) {
                BUTTON_MENU_EDIT = true
            }
            else{
                BUTTON_MENU_EDIT = false
            }

            ButtonView(
                title = if(EDIT_MODE) stringResource(R.string.SaveChanges) else stringResource(R.string.CreateMenu),
                enable = BUTTON_MENU_EDIT,
                modifier = Modifier.constrainAs(Button){
                    bottom.linkTo(parent.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                button_clicked = true

//                Log.d("info_update", "${text_name_menu.text} - ${text_price_menu.text} - ")

                if(EDIT_MODE){
                    menuViewModel.updateMenu(
                        idMenu = ID_MENU_EDIT,
                        menuTitle = text_name_menu.text,
                        menuPrice = text_price_menu.text,
                        menuClass = if(class_value_index == 0) false else true,
                        isWasher = checkedStateWasher.value,
                        isDryer = checkedStateDryer.value,
                        isService = checkedStateService.value,
                        navController = navController
                    )
                }
                else{
                    menuViewModel.insertMenu(
                        menuTitle = text_name_menu.text,
                        menuPrice = text_price_menu.text,
                        menuStore = STORE_ID,
                        menuClass = if(class_value_index == 0) false else true,
                        isWasher = checkedStateWasher.value,
                        isDryer = checkedStateDryer.value,
                        isService = checkedStateService.value,
                        navController = navController
                    )
                }
            }
        }
    }
}