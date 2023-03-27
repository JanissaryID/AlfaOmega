package com.example.alfaomega.api.transaction

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.api.income.IncomeViewModel
import com.example.alfaomega.api.machine.MachineApp
import com.example.alfaomega.api.machine.MachineModel
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.navigations.Screens
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TransactionViewModel: ViewModel() {
    @ExperimentalCoroutinesApi
    fun getTransactionActive() {
        Log.d("log_network", "Delay 3 seconds ----")
        viewModelScope.launch(Dispatchers.IO) {
            while (USER_TYPE == 3 && SCREEN_ACTIVE_NOW == Screens.Home.route){

                Log.d("log_network", "Delay 3 seconds")
                try {
                    TransactionApp.CreateInstance().fetchTransactionActive(
                        BearerToken = "Bearer " + TOKEN_API,
                        store = STORE_ID
                    ).enqueue(object :
                        Callback<ArrayList<TransactionModel>> {
                        override fun onResponse(
                            call: Call<ArrayList<TransactionModel>>,
                            response: Response<ArrayList<TransactionModel>>
                        ) {

                            Log.d("log_network", "Transaction Active : ${response.code()} ${response.body()}")

                            if (response.code() == 200) {
                                response.body()?.let {
                                    TRANSACTION_ACTIVE_RESPONSE = response.body()!!.filter { data -> data.transactionStateMachine != 6 } as ArrayList<TransactionModel>
                                    TRANSACTION_ACTIVE_STATE = 1
//                                    Log.d("debug_transaction", "Success get data transcaction")
                                }
                                if (TRANSACTION_ACTIVE_RESPONSE.isNullOrEmpty()) {
                                    TRANSACTION_ACTIVE_STATE = 3
                                }
                            }
                        }

                        override fun onFailure(call: Call<ArrayList<TransactionModel>>, t: Throwable) {
//                            Log.d("debug_transaction", "Fail get Data ${t.message.toString()}")
                            if (t.message == t.message) {
//                                Log.d("debug_transaction", "Failed")
                                TRANSACTION_ACTIVE_STATE = 2
                            }
                        }
                    })
                }
                catch (e: Exception) {
                    TRANSACTION_ERROR = e.message.toString()
//                    Log.d("debug_transaction", "ERROR $TRANSACTION_ERROR")
                }
                delay(5000L)
//                Thread.sleep(5000L)
            }
        }
    }

    fun getTransactionActiveOnce() {
        try {
            TransactionApp.CreateInstance().fetchTransactionActive(
                BearerToken = "Bearer " + TOKEN_API,
                store = STORE_ID
            ).enqueue(object :
                Callback<ArrayList<TransactionModel>> {
                override fun onResponse(
                    call: Call<ArrayList<TransactionModel>>,
                    response: Response<ArrayList<TransactionModel>>
                ) {
                    TRANSACTION_ACTIVE_STATE = 0

                    TRANSACTION_ACTIVE_RESPONSE.clear()

                    Log.d("log_network", "Transaction Active Once : ${response.code()} ${response.body()}")

                    if (response.code() == 200) {
                        response.body()?.let {
                            TRANSACTION_ACTIVE_RESPONSE = response.body()!!.filter { data -> data.transactionStateMachine != 6 } as ArrayList<TransactionModel>
                            TRANSACTION_ACTIVE_STATE = 1
//                            Log.d("debug_transaction", "Success get data transcaction")
                        }
                        if (TRANSACTION_ACTIVE_RESPONSE.isNullOrEmpty()) {
                            TRANSACTION_ACTIVE_STATE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<TransactionModel>>, t: Throwable) {
//                    Log.d("debug_transaction", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message) {
//                        Log.d("debug_transaction", "Failed")
                        TRANSACTION_ACTIVE_STATE = 2
                    }
                }
            })
        }
        catch (e: Exception) {
            TRANSACTION_ERROR = e.message.toString()
//            Log.d("debug_transaction", "ERROR $TRANSACTION_ERROR")
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun insertTransaction(
        transactionClass: Boolean,
        transactionMenu: String,
        transactionPrice: String,
        transactionPayment: Boolean,
        transactionCustomer: String,
        transactionStateMachine: Int,
        isWasher: Boolean,
        isDryer: Boolean,
        phoneCustomer: String,
        navController: NavController,
        userMoney: String,
        incomeViewModel: IncomeViewModel = IncomeViewModel()
    ){
        val current = LocalDateTime.now()

        val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = current.format(formatDay)


        val bodyUpdate = TransactionModel(
            transactionDate = date,
            transactionStore = STORE_ID,
            transactionClass = transactionClass,
            transactionMenu = transactionMenu,
            transactionPrice = transactionPrice,
            transactionPayment = transactionPayment,
            transactionCustomer = transactionCustomer,
            transactionStateMachine = transactionStateMachine,
            isWasher = isWasher,
            isDryer = isDryer,
            transactionAdmin = USER_NAME,
            userPhone = phoneCustomer,
            usermoney = userMoney
        )

        TransactionApp.CreateInstance().insertTransactions(
            BearerToken = "Bearer " + TOKEN_API,
            bodyUpdate
        ).enqueue(object :
            Callback<TransactionModel> {
            override fun onResponse(call: Call<TransactionModel>, response: Response<TransactionModel>) {
//                Log.d("debug", "Code Insert Transaction ${response}")
                Log.i("info_response", "Response Insert Transaction : ${response}")
                if(response.code() == 201){

                    incomeViewModel.fetchByStoreGetNull(incomeStat = true, income = transactionPrice)

                    navController.navigate(route = Screens.Home.route){
                        popUpTo(Screens.Home.route) {
                            inclusive = true
                        }
                    }
                    NEW_TRANSACATION_BUTTON = true
                    NEW_TRANSACATION_IS_DRYER = false
                    NEW_TRANSACATION_IS_WASHER = false
                }
            }

            override fun onFailure(call: Call<TransactionModel>, t: Throwable) {
//                Log.d("debug_transaction", t.message.toString())
                if (t.message == t.message){
                    TRANSACTION_ERROR = t.message.toString()
                    NEW_TRANSACATION_BUTTON = true
                }
            }
        })
    }

    @OptIn(ExperimentalPermissionsApi::class)
    fun updateTransaction(
        idTransaction: String,
        transactionStateMachine: Int,
        navController: NavController,
        bluetoothViewModel: BluetoothViewModel = BluetoothViewModel(),
        multiplePermissionState: MultiplePermissionsState,
    ){
        val bodyUpdate = TransactionModel(
            transactionStateMachine = transactionStateMachine,
        )

        try {
            TransactionApp.CreateInstance().updateMachine(
                BearerToken = "Bearer " + TOKEN_API,
                id = idTransaction,
                updateData = bodyUpdate).enqueue(object :
                Callback<TransactionModel> {
                override fun onResponse(call: Call<TransactionModel>, response: Response<TransactionModel>) {
                    if(response.code() == 200){
                        val responseBodyData = response.body()
                        if (responseBodyData!!.transactionStateMachine!! < 6){
                            getTransactionActiveOnce()

                            bluetoothViewModel.onMachineBluetooth(
                                address = MACHINE_MAC,
                                context = MY_CONTEXT!!,
                                multiplePermissionState = multiplePermissionState,
                                uuidDevice = "00001101-0000-1000-8000-00805f9b34fb",
                                navController = navController
                            )
                        }
                        else if(responseBodyData!!.transactionStateMachine!! > 5){
                            navController.navigate(route = Screens.Home.route){
                                popUpTo(Screens.Home.route) {
                                    inclusive = true
                                }
                            }
                        }
//                        else if(responseBodyData!!.transactionStateMachine!! == 0 || responseBodyData!!.transactionStateMachine!! == 3){
//
//                        }
//                        else{
//                            updateTransaction(
//                                idTransaction = idTransaction,
//                                transactionStateMachine = 6,
//                                navController = navController,
//                                bluetoothViewModel = bluetoothViewModel,
//                                multiplePermissionState = multiplePermissionState
//                            )
//                        }
                    }
                }

                override fun onFailure(call: Call<TransactionModel>, t: Throwable) {
//                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
                        TRANSACTION_ERROR = t.message.toString()
                    }
                }
            })
        }
        catch (e : Exception){
            TRANSACTION_ERROR = e.message.toString()
//            Log.d("debug_transaction", "ERROR $MACHINE_ERROR_MESSAGE")
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun getTransactionNow() {
        val current = LocalDateTime.now()

        val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = current.format(formatDay)

        try {
            TransactionApp.CreateInstance().fetchTransactionNow(
                BearerToken = "Bearer " + TOKEN_API,
                date = date,
                store = STORE_ID
            ).enqueue(object :
                Callback<ArrayList<TransactionModel>> {
                override fun onResponse(
                    call: Call<ArrayList<TransactionModel>>,
                    response: Response<ArrayList<TransactionModel>>
                ) {
                    TRANSACTION_STATE = 0

                    TRANSACTION_RESPONSE.clear()

                    Log.d("log_network", "Transactionn Now : ${response.code()} ${response.body()}")

                    if (response.code() == 200) {
                        response.body()?.let {
                            TRANSACTION_RESPONSE = response.body()!!
                            TRANSACTION_STATE = 1
//                            Log.d("debug_transaction", "Success get data transcaction $TRANSACTION_RESPONSE")
                        }
                        if (TRANSACTION_RESPONSE.isNullOrEmpty()) {
                            TRANSACTION_STATE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<TransactionModel>>, t: Throwable) {
//                    Log.d("debug_transaction", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message) {
//                        Log.d("debug_transaction", "Failed")
                        TRANSACTION_STATE = 2
                    }
                }
            })
        } catch (e: Exception) {
            TRANSACTION_ERROR = e.message.toString()
//            Log.d("debug_transaction", "ERROR $TRANSACTION_ERROR")
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun getTransactionNowDate() {
        val current = LocalDateTime.now()

        val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = current.format(formatDay)

        try {
            TransactionApp.CreateInstance().fetchTransactionNow(
                BearerToken = "Bearer " + TOKEN_API,
                date = if(DATE_PICK.isNullOrEmpty()) date else DATE_PICK,
                store = STORE_ID).enqueue(object :
                Callback<ArrayList<TransactionModel>> {
                override fun onResponse(
                    call: Call<ArrayList<TransactionModel>>,
                    response: Response<ArrayList<TransactionModel>>
                ) {
                    TRANSACTION_STATE = 0

                    TRANSACTION_RESPONSE.clear()

                    Log.d("log_network", "Trannsaction now Date : ${response.code()} ${response.body()}")

                    if (response.code() == 200) {
                        response.body()?.let {
                            TRANSACTION_RESPONSE = response.body()!!
                            TRANSACTION_STATE = 1
//                            Log.d("debug_transaction", "Success get data transcaction 1 $TRANSACTION_RESPONSE")
                        }
                        if (TRANSACTION_RESPONSE.isNullOrEmpty()) {
                            TRANSACTION_STATE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<TransactionModel>>, t: Throwable) {
//                    Log.d("debug_transaction", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message) {
//                        Log.d("debug_transaction", "Failed")
                        TRANSACTION_STATE = 2
                    }
                }
            })
        } catch (e: Exception) {
            TRANSACTION_ERROR = e.message.toString()
//            Log.d("debug_transaction", "ERROR $TRANSACTION_ERROR")
        }
    }
}