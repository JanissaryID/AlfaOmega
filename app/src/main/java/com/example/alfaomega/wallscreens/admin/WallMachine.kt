package com.example.alfaomega.wallscreens

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.api.machine.MachineModel
import com.example.alfaomega.api.machine.MachineViewModel
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.bluetoothprinter.PermissionsRequiredState
import com.example.alfaomega.components.button_view.ButtonViewV2
import com.example.alfaomega.view.admin.machine.MachineLoadData
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WallMachine(
    paddingValues: PaddingValues,
    navController: NavController,
    machineViewModel: MachineViewModel = MachineViewModel(),
    bluetoothViewModel: BluetoothViewModel,
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

    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index}

    var machine:ArrayList<MachineModel> by remember {
        mutableStateOf(arrayListOf())
    }

    if(MACHINE_SCREEN){
        TRANSACTION_SCREEN = false
    }

    if(USER_TYPE == 1){
        machine = LIST_MACHINE
    }
    else if(USER_TYPE == 3 && PROBLEM_MACHINE_STATE_SCREEN){
        machine = LIST_MACHINE
    }
    else{
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
    }

    ConstraintLayout(modifier = Modifier
        .padding(
            start = 16.dp,
            end = 16.dp,
            top = paddingValues.calculateTopPadding(),
            bottom = paddingValues.calculateBottomPadding()
        )
        .fillMaxSize()
    ) {
        val (Content, ButtonActive) = createRefs()

        Surface(
            color = Color.Transparent,
            modifier = Modifier.constrainAs(Content){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(ButtonActive.top)
            }
        ) {
            MachineLoadData(
                machineState = MACHINE_STATE,
                machine = machine,
                navController = navController,
                selectedIndex = selectedIndex,
                onItemClick = onItemClick,
            )
        }

        if (USER_TYPE == 3 && !PROBLEM_MACHINE_STATE_SCREEN){
            ButtonViewV2(
                title = stringResource(R.string.ActiveMachine),
                enable = MACHINE_BUTTON_UPDATE,
                modifier = Modifier.constrainAs(ButtonActive){
                    bottom.linkTo(parent.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                MACHINE_BUTTON_UPDATE = false
                MACHINE_LOADING = true

                bluetoothViewModel.requestBluetoothPermission()
                bluetoothViewModel.checkBluetoothCompatible()
                if(bluetoothViewModel.bluetoothAdapter != null){
                    bluetoothViewModel.onMachineBluetooth(
                        address = MACHINE_MAC,
                        context = MY_CONTEXT!!,
                        multiplePermissionState = multiplePermissionState,
                        uuidDevice = "00001101-0000-1000-8000-00805f9b34fb",
                        navController = navController
                    )
                }

//                bluetoothViewModel.onMachineBluetooth(
//                    address = MACHINE_MAC,
//                    context = MY_CONTEXT!!,
//                    multiplePermissionState = multiplePermissionState,
//                    uuidDevice = "00001101-0000-1000-8000-00805f9b34fb",
//                    navController = navController
//                )

//                machineViewModel.updateMachineStat(
//                    idMachine = MACHINE_ID,
//                    idTransaction = TRANSACATION_ID,
//                    navController = navController,
//                    bluetoothViewModel = bluetoothViewModel,
//                    multiplePermissionState = multiplePermissionState
//                )
            }
        }
    }

    if(MACHINE_LOADING){
        Surface(modifier = Modifier.fillMaxSize(), color = Color.DarkGray.copy(alpha = 0.5f)) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}