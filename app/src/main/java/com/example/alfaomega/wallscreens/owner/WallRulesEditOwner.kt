package com.example.alfaomega.wallscreens.owner

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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.api.problem.ProblemViewModel
import com.example.alfaomega.api.rules.RuleViewModel
import com.example.alfaomega.components.ButtonView
import com.example.alfaomega.components.button_view.ButtonViewV2
import com.example.alfaomega.navigations.Screens
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallRulesEditOwner(
    paddingValues: PaddingValues,
    ruleViewModel: RuleViewModel,
    navController: NavController,
    problemViewModel: ProblemViewModel = ProblemViewModel()
) {

    var button_clicked by remember { mutableStateOf(false) }
    var text_rule by remember {
        if(EDIT_MODE) mutableStateOf(TextFieldValue(RULE_TEXT_EDIT))
        else mutableStateOf(TextFieldValue(""))
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
                        if(USER_TYPE == 3 && PROBLEM_MACHINE_STATE_SCREEN){
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
                                        text = "${stringResource(R.string.MachineNumber)}",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                        color = MaterialTheme.colorScheme.surface
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text(
                                        text = "Type Machine",
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
                                        text = "   : $MACHINE_NUMBER",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                        color = MaterialTheme.colorScheme.surface
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text(
                                        text = "   : ${if(MACHINE_TYPE) "${stringResource(R.string.DryMachineTitle)}" else "${stringResource(R.string.WashmachineTitle)}"}, " +
                                                "${if(MACHINE_CLASS) "${stringResource(R.string.MenuTitan)}" else "${stringResource(R.string.MenuGiant)}"}",
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
                        }
                        Text(
                            text = if (USER_TYPE == 1 && PROBLEM_MACHINE_STATE_SCREEN) stringResource(R.string.ProblemTitle)
                            else if(USER_TYPE == 3 && PROBLEM_MACHINE_STATE_SCREEN) stringResource(R.string.ProblemTitle)
                            else stringResource(R.string.RuleTitle),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
//                            enabled = true,
                            enabled = if(USER_TYPE == 1 && PROBLEM_MACHINE_STATE_SCREEN && !EDIT_MODE) true else if(EDIT_MODE) false else true,
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

            if(!text_rule.text.isNullOrEmpty() && !button_clicked) {
                BUTTON_MENU_EDIT = true
            }
            else{
                BUTTON_MENU_EDIT = false
            }

            ButtonViewV2(
                title =
                if(!EDIT_MODE && PROBLEM_MACHINE_STATE_SCREEN && USER_TYPE == 1) stringResource(R.string.CreateProblem)
                else if(USER_TYPE == 1 && PROBLEM_MACHINE_STATE_SCREEN) stringResource(R.string.problemClearTitle)
                else if(USER_TYPE == 3 && PROBLEM_MACHINE_STATE_SCREEN) stringResource(R.string.CreateProblem)
                else if(EDIT_MODE) stringResource(com.example.alfaomega.R.string.SaveChanges)
                else stringResource(com.example.alfaomega.R.string.CreateRuleTitle),
                enable = BUTTON_MENU_EDIT,
                modifier = Modifier.constrainAs(Button){
                    bottom.linkTo(parent.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                button_clicked = true

                if(USER_TYPE == 1 && PROBLEM_MACHINE_STATE_SCREEN && EDIT_MODE){
                    problemViewModel.updateProblem(idProblem = ID_RULE_EDIT) // im lazy to create a new component
                    // so i reused component rule for problem machinne. id and text same name,
                    // but have different value in passing data lazycolumn
                    problemViewModel.fetchProblem()

                    navController.navigate(route = Screens.ReportMachine.route){
                        popUpTo(Screens.ReportMachine.route) {
                            inclusive = true
                        }
                    }
                }
                else if(USER_TYPE == 1 && PROBLEM_MACHINE_STATE_SCREEN && !EDIT_MODE){
                    problemViewModel.insertProblem(
                        idMachine = MACHINE_ID,
                        storeName = STORE_NAME,
                        date = date,
                        admin = USER_NAME,
                        store = STORE_ID,
                        numberMachine = MACHINE_NUMBER,
                        problem = text_rule.text,
                        navController = navController
                    )
                    problemViewModel.fetchProblem()

                    navController.navigate(route = Screens.ReportMachine.route){
                        popUpTo(Screens.ReportMachine.route) {
                            inclusive = true
                        }
                    }
                }
                else if(USER_TYPE == 3 && PROBLEM_MACHINE_STATE_SCREEN){
                    problemViewModel.insertProblem(
                        idMachine = MACHINE_ID,
                        storeName = STORE_NAME,
                        date = date,
                        admin = USER_NAME,
                        store = STORE_ID,
                        numberMachine = MACHINE_NUMBER,
                        problem = text_rule.text,
                        navController = navController
                    )
                    problemViewModel.fetchProblem()

                    navController.navigate(route = Screens.ReportMachine.route){
                        popUpTo(Screens.ReportMachine.route) {
                            inclusive = true
                        }
                    }
                }
                else{
                    if(EDIT_MODE){
                        ruleViewModel.updateRule(
                            idRule = ID_RULE_EDIT,
                            ruleText = text_rule.text,
                            navController = navController
                        )
                    }
                    else{
                        ruleViewModel.insertRule(
                            ruleText = text_rule.text,
                            navController = navController
                        )
                    }
                }
            }
        }
    }

}