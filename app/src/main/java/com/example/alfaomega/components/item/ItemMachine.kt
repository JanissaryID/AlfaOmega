package com.example.alfaomega.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.api.machine.MachineModel
import com.example.alfaomega.navigations.Screens

@Composable
fun ItemMachine(
    machineModel: MachineModel,
    usedMachine: Boolean,
    color: Color = MaterialTheme.colorScheme.surface,
    index: Int,
    selected: Boolean,
    onClick: (Int) -> Unit,
    navController: NavController
) {
//    val context = LocalContext.current

    Card(
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 4.dp, end = 4.dp),
//        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = if (usedMachine) BorderStroke(6.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)) else BorderStroke(6.dp, MaterialTheme.colorScheme.primary)
    ) {
        Surface(
            modifier = Modifier
                .padding(7.dp)
                .clickable {
                    if (!usedMachine) {
                        MACHINE_ID = machineModel.id!!
                        MACHINE_CLASS = machineModel.machineClass!!
                        MACHINE_TYPE = machineModel.machineType!!
                        MACHINE_NUMBER = machineModel.machineNumber!!
                        MACHINE_MAC = machineModel.macaddr!!
                        if(USER_TYPE == 1){
                            PROBLEM_MACHINE_STATE_SCREEN = true
                            PROBLEM_MACHINE_STATE = 0
                            LIST_PROBLEM_MACHINE.clear()
                            navController.navigate(route = Screens.ReportMachine.route){
                                popUpTo(Screens.ReportMachine.route) {
                                    inclusive = true
                                }
                            }
                        }
                        else if(PROBLEM_MACHINE_STATE_SCREEN){
                            PROBLEM_MACHINE_STATE_SCREEN = true
                            PROBLEM_MACHINE_STATE = 0
                            LIST_PROBLEM_MACHINE.clear()
                            navController.navigate(route = Screens.ReportMachine.route){
                                popUpTo(Screens.ReportMachine.route) {
                                    inclusive = true
                                }
                            }
                        }
                        else{
                            MACHINE_BUTTON_UPDATE = selected

                            onClick.invoke(index)
                        }
                    }
                },
            color = if (usedMachine){ MaterialTheme.colorScheme.secondary }
            else{ if (selected) MaterialTheme.colorScheme.primary else color },
            shape = RoundedCornerShape(5.dp),
            contentColor = if (selected) color else MaterialTheme.colorScheme.primary,
        ){
            Text(
                text = machineModel.machineNumber.toString(),
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                textAlign = TextAlign.Center,
                color = if (usedMachine) MaterialTheme.colorScheme.primary.copy(alpha = 0.6f) else if (selected) color  else MaterialTheme.colorScheme.primary
            )
        }
    }
}