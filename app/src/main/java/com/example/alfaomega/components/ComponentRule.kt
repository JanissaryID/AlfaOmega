package com.example.alfaomega.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel

@Composable
fun ComponentRule(
    ruleText: String,
    indexNumber: Int,
    idRule: String,
    solvedMachine: Boolean = false,
    machineNumber: String = "0",
    storeName: String = "Laundry",
    date: String = "",
    protoViewModel: ProtoViewModel,
    navController: NavController
) {
    Card(
//        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .padding(0.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(ROUND_CORNER.dp),
        colors = CardDefaults.cardColors(containerColor = if(solvedMachine) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) else MaterialTheme.colorScheme.primary)
    ) {
        Surface(
            color = Color.Transparent,
            shape = RoundedCornerShape(ROUND_CORNER.dp),
            modifier = Modifier
                .wrapContentHeight()
                .clickable(
                    enabled = !solvedMachine
                ) {
                    if (USER_TYPE == 1 && SCREEN_ACTIVE_NOW == Screens.RulesOwner.route) {
                        EDIT_MODE = true
                        RULE_TEXT_EDIT = ruleText
                        RULES_SCREEN_TYPE = true
                        ID_RULE_EDIT = idRule
                        protoViewModel.updateEditMode(status = EDIT_MODE)
                        navController.navigate(route = Screens.RulesEditOwner.route)
                    }
                }) {
            Column(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.Center
            ){
                Column(modifier = Modifier.fillMaxSize()) {
                    if(USER_TYPE == 1 && PROBLEM_MACHINE_STATE_SCREEN){
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
                                Text(
                                    text = stringResource(R.string.ProblemTitle),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(0.dp))
                            }
                            Column(modifier = Modifier.padding(start = 8.dp)) {
                                Text(
                                    text = ": $STORE_NAME",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = ": $MACHINE_NUMBER",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = ": ${if(MACHINE_TYPE) "${stringResource(R.string.DryMachineTitle)}" else "${stringResource(R.string.WashmachineTitle)}"}, " +
                                            "${if(MACHINE_CLASS) "${stringResource(R.string.MenuTitan)}" else "${stringResource(R.string.MenuGiant)}"}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = ": $date",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = ": $USER_NAME",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = ": $ruleText",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(0.dp))
                            }
                        }
                    }
                    else if(USER_TYPE == 3 && PROBLEM_MACHINE_STATE_SCREEN){
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
                                Text(
                                    text = stringResource(R.string.ProblemTitle),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(0.dp))
                            }
                            Column(modifier = Modifier.padding(start = 8.dp)) {
                                Text(
                                    text = ": $STORE_NAME",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = ": $MACHINE_NUMBER",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = ": ${if(MACHINE_TYPE) "${stringResource(R.string.DryMachineTitle)}" else "${stringResource(R.string.WashmachineTitle)}"}, " +
                                            "${if(MACHINE_CLASS) "${stringResource(R.string.MenuTitan)}" else "${stringResource(R.string.MenuGiant)}"}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = ": $date",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = ": $USER_NAME",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = ": $ruleText",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(0.dp))
                            }
                        }
                    }
//                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (!PROBLEM_MACHINE_STATE_SCREEN){
                            Text(
                                text = "${indexNumber.toString()}",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                color = MaterialTheme.colorScheme.surface
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = ruleText,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                color = MaterialTheme.colorScheme.surface
                            )
                        }
                    }
                }
            }
        }
    }
}