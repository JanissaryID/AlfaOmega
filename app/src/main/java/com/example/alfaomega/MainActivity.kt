package com.example.alfaomega

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
import com.example.alfaomega.api.machine.MachineViewModel
import com.example.alfaomega.api.menu.MenuViewModel
import com.example.alfaomega.api.store.StoreViewModel
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.navigations.NavGraphSetup
import com.example.alfaomega.proto.ProtoViewModel
import com.example.alfaomega.screens.ScreenMenu
import com.example.alfaomega.ui.theme.AlfaOmegaTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    val menuViewModel by viewModels<MenuViewModel>()
    val transactionViewModel by viewModels<TransactionViewModel>()
    val machineViewModel by viewModels<MachineViewModel>()
    val storeViewModel by viewModels<StoreViewModel>()

    private lateinit var protoViewModel: ProtoViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        protoViewModel = ViewModelProvider(this).get(ProtoViewModel::class.java)
        protoViewModel.getData.observe(this,{
            STORE_ID = it.storeid
        })

        setContent {
            AlfaOmegaTheme {
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
                        protoViewModel = protoViewModel
                    )

                    Log.i("info_response", "TIME : ${TIME_WASHER_GIANT}  ${TIME_WASHER_TITAN}  ${TIME_DRYER_GIANT}  ${TIME_DRYER_TITAN}  -  ${STORE_ID}")
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