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
import com.example.alfaomega.api.expenses.ExpensesViewModel
import com.example.alfaomega.api.income.IncomeViewModel
import com.example.alfaomega.api.log.LogViewModel
import com.example.alfaomega.api.machine.MachineViewModel
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.api.problem.ProblemViewModel
import com.example.alfaomega.api.qr.QrViewModel
import com.example.alfaomega.api.rules.RuleViewModel
import com.example.alfaomega.api.store.StoreViewModel
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.api.user.UserViewModel
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.navigations.NavGraphSetup
import com.example.alfaomega.proto.ProtoViewModel
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
    val problemViewModel by viewModels<ProblemViewModel>()
    val incomeViewModel by viewModels<IncomeViewModel>()
    val expensesViewModel by viewModels<ExpensesViewModel>()

    val bluetoothViewModel by viewModels<BluetoothViewModel>()
    val qrViewModel by viewModels<QrViewModel>()

    private lateinit var protoViewModel: ProtoViewModel

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        KEY_API = BuildConfig.KEY_API
        TOKEN_API = BuildConfig.TOKEN_API
        bluetoothViewModel.createInstance(this@MainActivity)
        bluetoothViewModel.checkBluetoothCompatible()

        protoViewModel = ViewModelProvider(this).get(ProtoViewModel::class.java)
        protoViewModel.getData.observe(this,{
            STORE_ID = it.storeid
            USER_NAME = it.username
            USER_TYPE = it.usertype
            ADDRESS_DEVICE = it.addressdevice
            OWNER_ID = it.ownerid
            UUID_DEVICE = it.uuidstring
        })

        setContent {
            AlfaOmegaTheme(
                dynamicColor = false,
                darkTheme = false
            ) {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ruleViewModel.getRules()

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
                        bluetoothViewModel = bluetoothViewModel,
                        problemViewModel = problemViewModel,
                        incomeViewModel = incomeViewModel,
                        expensesViewModel = expensesViewModel,
                        qrViewModel = qrViewModel
                    )

                    storeViewModel.FetchStore()

//                    bluetoothViewModel.showPairedDevice(context = this, multiplePermissionState = multiplePermissionState)
                    MY_CONTEXT = this

                    Log.i("info_response", "Proto : ${STORE_ID}  ${USER_NAME}  ${USER_TYPE} ${UUID_DEVICE} ${ADDRESS_DEVICE}")
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