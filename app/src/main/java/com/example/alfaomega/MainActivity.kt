package com.example.alfaomega

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.alfaomega.api.log.LogViewModel
import com.example.alfaomega.api.machine.MachineViewModel
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.api.rules.RuleViewModel
import com.example.alfaomega.api.store.StoreViewModel
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.api.user.UserViewModel
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.bluetoothprinter.PermissionsRequiredState
import com.example.alfaomega.navigations.NavGraphSetup
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.screens.ScreenMenu
import com.example.alfaomega.ui.theme.AlfaOmegaTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    val menuViewModel by viewModels<MenuViewModel>()
    val transactionViewModel by viewModels<TransactionViewModel>()
    val machineViewModel by viewModels<MachineViewModel>()
    val storeViewModel by viewModels<StoreViewModel>()
    val ruleViewModel by viewModels<RuleViewModel>()
    val userViewModel by viewModels<UserViewModel>()
    val logViewModel by viewModels<LogViewModel>()

    val bluetoothViewModel by viewModels<BluetoothViewModel>()

    private lateinit var protoViewModel: ProtoViewModel
//    private lateinit var bluetoothViewModel: BluetoothViewModel

    @RequiresApi(Build.VERSION_CODES.R)
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bluetoothViewModel.createInstance(this@MainActivity)
        bluetoothViewModel.checkBluetoothCompatible()

        protoViewModel = ViewModelProvider(this).get(ProtoViewModel::class.java)
        protoViewModel.getData.observe(this,{
            STORE_ID = it.storeid
            USER_NAME = it.username
            USER_TYPE = it.usertype
            ADDRESS_DEVICE = it.addressdevice
            OWNER_ID = it.ownerid
        })

        setContent {
            AlfaOmegaTheme {

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

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    navController = rememberNavController()
                    NavGraphSetup(
                        navController = navController,
                        menuViewModel = menuViewModel,
                        transactionViewModel = transactionViewModel,
                        machineViewModel = machineViewModel,
                        storeViewModel = storeViewModel,
                        protoViewModel = protoViewModel,
                        ruleViewModel = ruleViewModel,
                        userViewModel = userViewModel,
                        logViewModel = logViewModel,
                        bluetoothViewModel = bluetoothViewModel
                    )

//                    bluetoothViewModel.showPairedDevice(context = this, multiplePermissionState = multiplePermissionState)
                    MY_CONTEXT = this

                    Log.i("info_response", "Proto : ${STORE_ID}  ${USER_NAME}  ${USER_TYPE}")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AlfaOmegaTheme {
        Greeting("Android")
    }
}