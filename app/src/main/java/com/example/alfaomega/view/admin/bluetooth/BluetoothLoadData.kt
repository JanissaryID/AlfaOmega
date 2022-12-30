package com.example.alfaomega.view.admin.bluetooth

import android.bluetooth.BluetoothDevice
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.api.machine.MachineModel
import com.example.alfaomega.view.admin.machine.MachineLazyGrid

@Composable
fun BluetoothLoadData(
    bluetoothState: Int,
//    selectedIndex: Int,
    bluetooth: Set<BluetoothDevice>,
    navController: NavController,
//    onItemClick: (Int) -> Unit,
) {
    val context = LocalContext.current

    when (bluetoothState) {
        0 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        1 -> {
            if (!bluetooth.isNullOrEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    BluetoothLazyColumn(
                        bluetoothDevice = bluetooth.toList(),
//                        protoViewModel = pro,
                        navController = navController
                    )
                }
            }
        }
        2 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Can't load data",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
//            Log.d("debug", "Error")
            Toast.makeText(context, "Can't load data", Toast.LENGTH_SHORT).show()
        }
        3 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {

                    val (StoreImage, TextEmpty) = createRefs()

                    Icon(painter = painterResource(
                        id = R.drawable.ic_twotone_list_alt_24),
                        contentDescription = stringResource(R.string.BluetoothEmpty),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .wrapContentHeight()
                            .size(200.dp)
                            .constrainAs(StoreImage)
                            {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                    )

                    Text(
                        text = stringResource(R.string.BluetoothEmpty),
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .wrapContentHeight()
                            .constrainAs(TextEmpty)
                            {
                                top.linkTo(StoreImage.bottom, 8.dp)
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                    )
                }
            }
        }
        4 -> {
            Toast.makeText(context, stringResource(R.string.TryAgainTitle), Toast.LENGTH_SHORT).show()
        }
    }
}