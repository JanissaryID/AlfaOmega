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
import com.example.alfaomega.navigations.Screens

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
                            text = if (USER_TYPE == 1 && PROBLEM_MACHINE_STATE_SCREEN) stringResource(R.string.ProblemTitle) else stringResource(R.string.RuleTitle),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextField(
                            enabled = if(USER_TYPE == 1 && PROBLEM_MACHINE_STATE_SCREEN) false else true,
                            value = text_rule,
                            onValueChange ={
                                text_rule = it
                            },
                            singleLine = false,
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                textColor = MaterialTheme.colorScheme.surfaceVariant,
                                disabledTextColor = MaterialTheme.colorScheme.surfaceVariant,
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth().height(200.dp),
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

            ButtonView(
                title = if(USER_TYPE == 1 && PROBLEM_MACHINE_STATE_SCREEN) stringResource(R.string.problemClearTitle) else if(EDIT_MODE) stringResource(com.example.alfaomega.R.string.SaveChanges) else stringResource(com.example.alfaomega.R.string.CreateRuleTitle),
                enable = BUTTON_MENU_EDIT,
                modifier = Modifier.constrainAs(Button){
                    bottom.linkTo(parent.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                button_clicked = true

                if(USER_TYPE == 1 && PROBLEM_MACHINE_STATE_SCREEN){
                    problemViewModel.updateProblem(idProblem = ID_RULE_EDIT) // im lazy to create a new component
                    // so i reused component rule for problem machinne. id and text same name,
                    // but have different value in passing data lazycolumn

                    navController.navigate(route = Screens.ReportMachine.route){
                        popUpTo(Screens.Home.route) {
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