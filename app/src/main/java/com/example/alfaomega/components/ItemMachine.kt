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
import com.example.alfaomega.*
import com.example.alfaomega.api.machine.MachineModel

@Composable
fun ItemMachine(
    machineModel: MachineModel,
    usedMachine: Boolean,
    color: Color = MaterialTheme.colorScheme.surface,
    index: Int,
    selected: Boolean,
    onClick: (Int) -> Unit
) {
//    val context = LocalContext.current

    Card(
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 4.dp, end = 4.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = if (usedMachine) BorderStroke(6.dp, MaterialTheme.colorScheme.secondary) else BorderStroke(6.dp, MaterialTheme.colorScheme.primary)
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

                        MACHINE_BUTTON_UPDATE = selected

//                        if(!machineModel.machineClass!! && !machineModel.machineType!!){
//                            MACHINE_TIME = TIME_WASHER_GIANT
//                        }
//                        else if(machineModel.machineClass!! && !machineModel.machineType!!){
//                            MACHINE_TIME = TIME_WASHER_TITAN
//                        }
//                        else if(!machineModel.machineClass!! && machineModel.machineType!!){
//                            MACHINE_TIME = TIME_DRYER_GIANT
//                        }
//                        else if(machineModel.machineClass!! && machineModel.machineType!!){
//                            MACHINE_TIME = TIME_DRYER_TITAN
//                        }

                        onClick.invoke(index)
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
                textAlign = TextAlign.Center
            )
        }
    }
}