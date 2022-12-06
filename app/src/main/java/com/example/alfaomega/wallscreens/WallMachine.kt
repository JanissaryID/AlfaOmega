package com.example.alfaomega.wallscreens

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.api.machine.MachineModel
import com.example.alfaomega.view.machine.MachineLoadData

@Composable
fun WallMachine(
    paddingValues: PaddingValues,
    navController: NavController,
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index}

    var machine:ArrayList<MachineModel> by remember {
        mutableStateOf(arrayListOf())
    }

    TRANSACTION_SCREEN = false

    if(TRANSACATION_CLASS == false && TRANSACATION_STATUS_MACHINE == 0){
        machine = LIST_MACHINE_WASHER_GIANT
    }
    else if(TRANSACATION_CLASS == true && TRANSACATION_STATUS_MACHINE == 0){
        machine = LIST_MACHINE_WASHER_TITAN
    }
    else if(TRANSACATION_CLASS == false && TRANSACATION_STATUS_MACHINE == 3){
        machine = LIST_MACHINE_DRYER_GIANT
    }
    else if(TRANSACATION_CLASS == true && TRANSACATION_STATUS_MACHINE == 3){
        machine = LIST_MACHINE_DRYER_TITAN
    }

    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {
        MachineLoadData(
            machineState = MACHINE_STATE,
            machine = machine,
            navController = navController,
            selectedIndex = selectedIndex,
            onItemClick = onItemClick
        )
    }
}