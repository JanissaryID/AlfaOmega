package com.example.alfaomega.wallscreens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.BLUETOOTH_STATE
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.view.admin.bluetooth.BluetoothLoadData

@Composable
fun WallBluetooth(
    paddingValues: PaddingValues,
    navController: NavController,
    protoViewModel: ProtoViewModel,
    bluetoothViewModel: BluetoothViewModel
) {
    Surface(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!bluetoothViewModel.devices.isNullOrEmpty()){
                BluetoothLoadData(
                    navController = navController,
                    bluetooth = bluetoothViewModel.devices!!,
                    bluetoothState = BLUETOOTH_STATE
                )
            }
            else{
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}