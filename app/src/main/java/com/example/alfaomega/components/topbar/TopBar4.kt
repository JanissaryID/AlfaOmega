package com.example.alfaomega.components.topbar

import android.app.DatePickerDialog
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.R
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.api.rules.RuleViewModel
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.api.user.UserViewModel
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun TopBar4(
    tittleScreen: String,
    navController: NavController,
    screenBack: String,
    icon: Int,
    description: String,
    actionNav: String,
    menuViewModel: MenuViewModel = MenuViewModel(),
    ruleViewModel: RuleViewModel = RuleViewModel(),
    userViewModel: UserViewModel = UserViewModel(),
    transactionViewModel: TransactionViewModel = TransactionViewModel(),
    bluetoothViewModel: BluetoothViewModel,
    multiplePermissionState: MultiplePermissionsState
) {

    val activity = LocalContext.current

    SmallTopAppBar(
        title = {
            Text(
            text = tittleScreen,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        ) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            Surface(color = Color.Transparent, shape = RoundedCornerShape(100), modifier = Modifier.wrapContentSize()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = "Back",
                    modifier = Modifier.clickable {
                        navController.navigate(route = screenBack) {
                            popUpTo(screenBack) {
                                inclusive = true
                            }
                        }
                    },
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        actions = {
            Surface(shape = RoundedCornerShape(100), modifier = Modifier.wrapContentSize().padding(end = 8.dp)) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = description,
                    modifier = Modifier.clickable {
                        if(USER_TYPE == 1){
                            if(MENU_SCREEN_TYPE && EDIT_MODE){
                                menuViewModel.deleteMenu(navController = navController, idMenu = ID_MENU_EDIT)
                                navController.navigate(route = actionNav) {
                                    popUpTo(actionNav) {
                                        inclusive = true
                                    }
                                }
                            }
                            if(RULES_SCREEN_TYPE && EDIT_MODE){
                                ruleViewModel.deleteRule(navController = navController, idRule = ID_RULE_EDIT)
                                navController.navigate(route = actionNav) {
                                    popUpTo(actionNav) {
                                        inclusive = true
                                    }
                                }
                            }
                            if(USER_SCREEN_TYPE && EDIT_MODE){
                                userViewModel.deleteUser(navController = navController, iduser = ID_USER_EDIT)
                                navController.navigate(route = actionNav) {
                                    popUpTo(actionNav) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                        else{
                            Log.d("Check_Print", "Check_Print")
                            bluetoothViewModel.requestBluetoothPermission()
                            bluetoothViewModel.checkBluetoothCompatible()
                            bluetoothViewModel.connectBluetooth(
                                address = ADDRESS_DEVICE,
                                context = MY_CONTEXT!!,
                                multiplePermissionState = multiplePermissionState,
                                uuidDevice = UUID_DEVICE
                            )
                            Log.d("Check_Print", "Stat Connect = $STAT_BLUETOOTH_CONNECT")
                        }
                    }
                )
            }
        }
    )
}