package com.example.alfaomega.components.topbar

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.api.user.UserViewModel
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
    context: Context,
    containerColor: Color = Color.Transparent,
    colorFont: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    userViewModel: UserViewModel = UserViewModel()
) {
    SmallTopAppBar(
        title = {
            Text(
            text = tittleScreen,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = colorFont
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = containerColor),
        actions = {
            Surface(color = Color.Transparent, shape = RoundedCornerShape(100), modifier = Modifier.wrapContentSize().padding(end = 8.dp)) {
                Icon(
                    painter = painterResource(id = icon),
                    tint = colorFont,
                    contentDescription = description,
                    modifier = Modifier.clickable {
                        if(USER_TYPE != 3 && SCREEN_ACTIVE_NOW == Screens.Home.route){
                            userViewModel.updateStatUser(OWNER_ID, false)
                            protoViewModel.updateNameUser(Nameuser = "")
                            protoViewModel.updateTypeUser(TypeUser = 0)
                            protoViewModel.updateStoreID(keyStore = "")
                            multiplePermissionState.launchMultiplePermissionRequest()
                            bluetoothViewModel.requestBluetoothPermission()

                            if(BLUETOOTH_STATE_ON){
                                navController.navigate(route = route)
                                {
                                    popUpTo(route) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                        else{
                            multiplePermissionState.launchMultiplePermissionRequest()
                            bluetoothViewModel.requestBluetoothPermission()
                            if(BLUETOOTH_STATE_ON){
                                navController.navigate(route = route)
                                {
                                    popUpTo(route) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    )
}