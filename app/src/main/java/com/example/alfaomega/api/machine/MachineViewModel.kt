package com.example.alfaomega.api.machine

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.api.log.LogViewModel
import com.example.alfaomega.api.transaction.TransactionViewModel
import com.example.alfaomega.bluetoothprinter.BluetoothViewModel
import com.example.alfaomega.navigations.Screens
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MachineViewModel: ViewModel() {

    fun getMachine(){
        try {
            MachineApp.CreateInstance().fetchMachine(
                BearerToken = "Bearer " + TOKEN_API,
                store = STORE_ID
            ).enqueue(object :
                Callback<ArrayList<MachineModel>> {
                override fun onResponse(call: Call<ArrayList<MachineModel>>, response: Response<ArrayList<MachineModel>>) {
//                    MENU_STATE = 0

                    Log.d("log_network", "Machine : ${response.code()} ${response.body()}")

                    LIST_MACHINE_WASHER_GIANT.clear()
                    LIST_MACHINE_DRYER_GIANT.clear()
                    LIST_MACHINE_WASHER_TITAN.clear()
                    LIST_MACHINE_DRYER_TITAN.clear()

                    if(response.code() == 200){
                        response.body()?.let {
                            LIST_MACHINE_WASHER_GIANT = response.body()!!.filter { menu -> menu.machineType == false && menu.machineClass == false} as ArrayList<MachineModel>
                            LIST_MACHINE_DRYER_GIANT = response.body()!!.filter { menu -> menu.machineType == true && menu.machineClass == false} as ArrayList<MachineModel>
                            LIST_MACHINE_WASHER_TITAN = response.body()!!.filter { menu -> menu.machineType == false && menu.machineClass == true} as ArrayList<MachineModel>
                            LIST_MACHINE_DRYER_TITAN = response.body()!!.filter { menu -> menu.machineType == true && menu.machineClass == true} as ArrayList<MachineModel>
                            MACHINE_STATE = 1
                        }
                        if (LIST_MACHINE_WASHER_GIANT.isNullOrEmpty()){
                            MACHINE_STATE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<MachineModel>>, t: Throwable) {
//                    Log.d("debug menu", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
//                        Log.d("debug menu", "Failed")
                        MACHINE_STATE = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            MACHINE_ERROR_MESSAGE = e.message.toString()
//            Log.d("debug menu", "ERROR $MACHINE_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    fun getMachineList(){
        try {
            MachineApp.CreateInstance().fetchMachine(
                BearerToken = "Bearer " + TOKEN_API,
                store = STORE_ID
            ).enqueue(object :
                Callback<ArrayList<MachineModel>> {
                override fun onResponse(call: Call<ArrayList<MachineModel>>, response: Response<ArrayList<MachineModel>>) {
                    Log.d("debug_machine", "Machine ${response}")
//                    MENU_STATE = 0

                    Log.d("log_network", "Machine list : ${response.code()} ${response.body()}")

                    LIST_MACHINE.clear()
                    if(response.code() == 200){
                        response.body()?.let {
                            LIST_MACHINE = response.body()!!
                            MACHINE_STATE = 1
                        }
                        if (LIST_MACHINE.isNullOrEmpty()){
                            MACHINE_STATE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<MachineModel>>, t: Throwable) {
//                    Log.d("debug menu", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
//                        Log.d("debug menu", "Failed")
                        MACHINE_STATE = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            MACHINE_ERROR_MESSAGE = e.message.toString()
//            Log.d("debug menu", "ERROR $MACHINE_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    fun updateMachineStat(
        idMachine: String,
        idTransaction: String,
        navController: NavController,
        logViewModel: LogViewModel = LogViewModel(),
        transactionViewModel: TransactionViewModel = TransactionViewModel(),
        bluetoothViewModel: BluetoothViewModel,
        multiplePermissionState: MultiplePermissionsState,
    ){
        val bodyDataUpdate = MachineModel(
            machineStatus = true,
            transactionId = idTransaction,
            machineTime = 0,
        )

        try {
            MachineApp.CreateInstance().updateMachine(
                BearerToken = "Bearer " + TOKEN_API,
                id = idMachine,
                bodyDataUpdate
            ).enqueue(object :
                Callback<MachineModel> {
                @RequiresApi(Build.VERSION_CODES.TIRAMISU)
                override fun onResponse(call: Call<MachineModel>, response: Response<MachineModel>) {
                    Log.d("log_network", "Machine : ${response.code()} ${response.body()}")
                    if(response.code() == 200){
                        val responseBodyData = response.body()
                        if (responseBodyData!!.machineStatus!!){


                            logViewModel.insertLog(
                                log = response.toString(),
                                machineNumber = MACHINE_NUMBER,
                                machineStore = STORE_ID,
                                codeResponse = response.code().toString(),
                                machineStatus = true,
                                machineClass = MACHINE_CLASS,
                                machineType = MACHINE_TYPE
                            )
                            if(!MACHINE_TYPE){
                                transactionViewModel.updateTransaction(
                                    navController = navController,
                                    transactionStateMachine = 1,
                                    idTransaction = idTransaction,
//                                    bluetoothViewModel = bluetoothViewModel,
//                                    multiplePermissionState = multiplePermissionState
                                )
                            }
                            else{
                                transactionViewModel.updateTransaction(
                                    navController = navController,
                                    transactionStateMachine = 4,
                                    idTransaction = idTransaction,
//                                    bluetoothViewModel = bluetoothViewModel,
//                                    multiplePermissionState = multiplePermissionState
                                )
                            }
                        }
                        else{
                            updateMachineStat(
                                idMachine = idMachine,
                                idTransaction = idTransaction,
//                                timeMachine = timeMachine,
                                navController = navController,
                                bluetoothViewModel = bluetoothViewModel,
                                multiplePermissionState = multiplePermissionState
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<MachineModel>, t: Throwable) {
//                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            MACHINE_ERROR_MESSAGE = e.message.toString()
//            Log.d("debug", "ERROR $MACHINE_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    fun updateMachineStatV2(
        idMachine: String,
        idTransaction: String,
        navController: NavController,
        logViewModel: LogViewModel = LogViewModel(),
        transactionViewModel: TransactionViewModel = TransactionViewModel(),
//        bluetoothViewModel: BluetoothViewModel,
//        multiplePermissionState: MultiplePermissionsState,
    ){
        val bodyDataUpdate = MachineModel(
            machineStatus = true,
            transactionId = idTransaction,
            machineTime = 0,
        )

        try {
            MachineApp.CreateInstance().updateMachine(
                BearerToken = "Bearer " + TOKEN_API,
                id = idMachine,
                bodyDataUpdate
            ).enqueue(object :
                Callback<MachineModel> {
                @RequiresApi(Build.VERSION_CODES.TIRAMISU)
                override fun onResponse(call: Call<MachineModel>, response: Response<MachineModel>) {
                    Log.d("log_network", "Machine : ${response.code()} ${response.body()}")
                    if(response.code() == 200){
                        val responseBodyData = response.body()
                        if (responseBodyData!!.machineStatus!!){
                            logViewModel.insertLog(
                                log = response.toString(),
                                machineNumber = MACHINE_NUMBER,
                                machineStore = STORE_ID,
                                codeResponse = response.code().toString(),
                                machineStatus = true,
                                machineClass = MACHINE_CLASS,
                                machineType = MACHINE_TYPE
                            )
                            if(!MACHINE_TYPE){
                                transactionViewModel.updateTransaction(
                                    navController = navController,
                                    transactionStateMachine = 1,
                                    idTransaction = idTransaction,
//                                    bluetoothViewModel = bluetoothViewModel,
//                                    multiplePermissionState = multiplePermissionState
                                )
                            }
                            else{
                                transactionViewModel.updateTransaction(
                                    navController = navController,
                                    transactionStateMachine = 4,
                                    idTransaction = idTransaction,
//                                    bluetoothViewModel = bluetoothViewModel,
//                                    multiplePermissionState = multiplePermissionState
                                )
                            }
                        }
                        else{
                            updateMachineStatV2(
                                idMachine = idMachine,
                                idTransaction = idTransaction,
                                navController = navController,
//                                bluetoothViewModel = bluetoothViewModel,
//                                multiplePermissionState = multiplePermissionState
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<MachineModel>, t: Throwable) {
//                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            MACHINE_ERROR_MESSAGE = e.message.toString()
//            Log.d("debug", "ERROR $MACHINE_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    fun updateMachine(
        idMachine: String,
        idTransaction: String,
        timeMachine: Int,
        navController: NavController,
        logViewModel: LogViewModel = LogViewModel(),
        transactionViewModel: TransactionViewModel = TransactionViewModel()
    ){
        val bodyDataUpdate = MachineModel(
            machineStatus = true,
            transactionId = idTransaction,
            machineTime = timeMachine,
        )

        try {
            MachineApp.CreateInstance().updateMachine(
                BearerToken = "Bearer " + TOKEN_API,
                id = idMachine,
                bodyDataUpdate
            ).enqueue(object :
                Callback<MachineModel> {
                @RequiresApi(Build.VERSION_CODES.TIRAMISU)
                override fun onResponse(call: Call<MachineModel>, response: Response<MachineModel>) {
//                    Log.d("debug", "Code Update Machine ${response}")
                    if(response.code() == 200){
                        val responseBodyData = response.body()
//                        Log.d("debug", "Body Update Machine ${response.body()}")
                        if (responseBodyData!!.machineStatus!!){

                            MACHINE_BUTTON_UPDATE = true

                            logViewModel.insertLog(
                                log = response.toString(),
                                machineNumber = MACHINE_NUMBER,
                                machineStore = STORE_ID,
                                codeResponse = response.code().toString(),
                                machineStatus = true,
                                machineClass = MACHINE_CLASS,
                                machineType = MACHINE_TYPE
                            )

                            transactionViewModel.getTransactionActiveOnce()

                            navController.navigate(route = Screens.Home.route){
                                popUpTo(Screens.Home.route) {
                                    inclusive = true
                                }
                            }
                        }
                        else{
                            updateMachine(
                                idMachine = idMachine,
                                idTransaction = idTransaction,
                                timeMachine = timeMachine,
                                navController = navController
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<MachineModel>, t: Throwable) {
//                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            MACHINE_ERROR_MESSAGE = e.message.toString()
//            Log.d("debug", "ERROR $MACHINE_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }
}