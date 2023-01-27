package com.example.alfaomega.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel

@Composable
fun ItemBluetoothDevice(
    nameDevice: String,
    addressDevice: String,
    uuidDevice: String,
    protoViewModel: ProtoViewModel,
) {
    Card(
//        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier.padding(0.dp),
        shape = RoundedCornerShape(ROUND_CORNER.dp),
        colors = if(addressDevice == ADDRESS_DEVICE) CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.surface)
                else CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
            contentColor = MaterialTheme.colorScheme.surface)
    ) {
        Surface(
            color = Color.Transparent,
            shape = RoundedCornerShape(ROUND_CORNER.dp),
            modifier = Modifier.clickable {

                protoViewModel.updateAddressDevice(AddressDevice = addressDevice)
                protoViewModel.updateUUID(UUID_Device = uuidDevice)

            }) {
            Column() {
                Column(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                ){
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = nameDevice,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = MaterialTheme.typography.labelLarge.fontSize,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = addressDevice,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        )
                    }
                }
            }
        }
    }
}