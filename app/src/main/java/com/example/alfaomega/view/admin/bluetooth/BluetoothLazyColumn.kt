package com.example.alfaomega.view.admin.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.LIST_PROBLEM_MACHINE
import com.example.alfaomega.components.ItemBluetoothDevice
import com.example.alfaomega.proto.ProtoViewModel

@SuppressLint("MissingPermission")
@Composable
fun BluetoothLazyColumn(
    bluetoothDevice: List<BluetoothDevice>,
    protoViewModel: ProtoViewModel,
    navController: NavController,
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        itemsIndexed(items = bluetoothDevice) { index, device ->

            ItemBluetoothDevice(
                nameDevice = device.name,
                addressDevice = device.address,
                uuidDevice = if(device.name == "RPP02N") "UUID 00000000-0000-1000-8000-00805f9b34fb" else "${device.uuids}",
                protoViewModel = protoViewModel
            )
//            Log.d("get_log", "Device = ${device.name} uuid = ${if(device.name == "RPP02N") "${device.uuids[1].toString()}" else ""}")
        }
    }
}