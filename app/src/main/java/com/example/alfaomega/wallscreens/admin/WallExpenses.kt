package com.example.alfaomega.wallscreens.admin

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.api.expenses.ExpensesViewModel
import com.example.alfaomega.api.income.IncomeViewModel
import com.example.alfaomega.components.button_view.ButtonViewV2
import com.example.alfaomega.navigations.Screens
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun WallExpenses(
    paddingValues: PaddingValues,
    navController: NavController,
    incomeViewModel: IncomeViewModel = IncomeViewModel(),
    expensesViewModel: ExpensesViewModel = ExpensesViewModel()
) {
    var button_clicked by remember { mutableStateOf(false) }
    var text_rule by remember {
        if(EDIT_MODE) mutableStateOf(TextFieldValue(RULE_TEXT_EDIT))
        else mutableStateOf(TextFieldValue(""))
    }

    var text_nominal by remember {
        mutableStateOf("")
    }

    val current = LocalDateTime.now()

    val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val date = current.format(formatDay)

    Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding(), start = 16.dp, end = 16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {

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
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Column(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ){
                        Row(){
                            Column() {
                                Text(
                                    text = "${stringResource(R.string.OutletTitle)}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = stringResource(R.string.DateTitle),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = stringResource(R.string.Admin),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                            Column() {
                                Text(
                                    text = "   : $STORE_NAME",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "   : $date",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "   : $USER_NAME",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(0.dp))
                        Text(
                            text = "Nominal",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            value = text_nominal,
                            onValueChange ={
                                text_nominal = it.filter { it.isDigit() }
                            },
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                containerColor = MaterialTheme.colorScheme.surface,
                                textColor = MaterialTheme.colorScheme.primary,
                                disabledTextColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, keyboardType = KeyboardType.Number),
                            readOnly = false
//                            readOnly = if(USER_TYPE == 3 && SCREEN_ACTIVE_NOW == Screens.ExpensesStore.route) false else true
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Note",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            enabled = true,
                            value = text_rule,
                            onValueChange ={
                                text_rule = it
                            },
                            singleLine = false,
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                containerColor = MaterialTheme.colorScheme.surface,
                                textColor = MaterialTheme.colorScheme.primary,
                                disabledTextColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                        )
                    }
                }
            }

            if(!text_rule.text.isNullOrEmpty() && !text_nominal.isNullOrEmpty() && !button_clicked) {
                BUTTON_MENU_EDIT = true
            }
            else{
                BUTTON_MENU_EDIT = false
            }

            ButtonViewV2(
                title = "Save",
                enable = BUTTON_MENU_EDIT,
                modifier = Modifier.constrainAs(Button){
                    bottom.linkTo(parent.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                button_clicked = true

                incomeViewModel.fetchByStoreGetNull(incomeStat = false, expenses = text_nominal)
                expensesViewModel.insertExpenses(expenses = text_nominal, note = text_rule.text)

                if(USER_TYPE == 3){
                    navController.navigate(route = Screens.StoreProfile.route){
                        popUpTo(Screens.StoreProfile.route) {
                            inclusive = true
                        }
                    }
                }
                else{
                    navController.navigate(route = Screens.ExpensesOwner.route){
                        popUpTo(Screens.ExpensesOwner.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}