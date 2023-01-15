package com.example.alfaomega.view.admin.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
                protoViewModel = protoViewModel
            )
        }
    }
}