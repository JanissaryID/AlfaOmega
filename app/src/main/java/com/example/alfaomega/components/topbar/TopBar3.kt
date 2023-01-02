package com.example.alfaomega.components.topbar

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.alfaomega.R
import com.example.alfaomega.USER_TYPE
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun TopBar3(
    tittleScreen: String,
    navController: NavController,
    route: String,
    icon: Int,
    description: String,
    protoViewModel: ProtoViewModel,
    bluetoothViewModel: BluetoothViewModel,
    multiplePermissionState: MultiplePermissionsState,
    context: Context
) {
    SmallTopAppBar(
        title = {
            Text(
            text = tittleScreen,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
        },
        actions = {
            Surface(shape = RoundedCornerShape(100), modifier = Modifier.wrapContentSize()) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = description,
                    modifier = Modifier.clickable {
                        if(USER_TYPE != 3){
                            protoViewModel.updateNameUser(Nameuser = "")
                            protoViewModel.updateTypeUser(TypeUser = 0)
                            protoViewModel.updateStoreID(keyStore = "")
                            Log.i("info_response", "Check Bluetooth 1")
                            Log.i("info_response", "${route} 1")
                            multiplePermissionState.launchMultiplePermissionRequest()
                            bluetoothViewModel.requestBluetoothPermission()

//                            navController.navigate(route = route)
                            navController.navigate(route = route) {
                                popUpTo(route) {
                                    inclusive = true
                                }
                            }
                        }
                        else{

                            Log.i("info_response", "Check Bluetooth")
                            Log.i("info_response", "${route}")
                            multiplePermissionState.launchMultiplePermissionRequest()
                            bluetoothViewModel.requestBluetoothPermission()
                            navController.navigate(route = route) {
                                popUpTo(route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                )
            }
        }
    )
}