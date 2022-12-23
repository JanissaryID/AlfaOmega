package com.example.alfaomega.api.log

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.api.menu.MenuApp
import com.example.alfaomega.api.menu.MenuModel
import com.example.alfaomega.navigations.Screens
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LogViewModel: ViewModel() {

//    fun getLog(){
//        try {
//            LogApp.CreateInstance().fetchLogMachine(store = STORE_ID).enqueue(object :
//                Callback<ArrayList<MenuModel>> {
//                override fun onResponse(call: Call<ArrayList<MenuModel>>, response: Response<ArrayList<MenuModel>>) {
//                    MENU_STATE = 0
//                    if(response.code() == 200){
//                        response.body()?.let {
//                            MENU_LIST_GIANT_RESPONSE = response.body()!!.filter { menu -> menu.menuClass == false } as ArrayList<MenuModel>
//                            MENU_LIST_TITAN_RESPONSE = response.body()!!.filter { menu -> menu.menuClass == true } as ArrayList<MenuModel>
//
//                            MENU_STATE = 1
////                            Log.i("i`nfo_response", "Data GIANT : ${MENU_LIST_GIANT_RESPONSE}")
//                        }
//                        if (MENU_LIST_GIANT_RESPONSE.isNullOrEmpty()){
//                            MENU_STATE = 3
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<MenuModel>>, t: Throwable) {
//                    Log.d("debug menu", "Fail get Data ${t.message.toString()}")
//                    if (t.message == t.message){
//                        Log.d("debug menu", "Failed")
//                        MENU_STATE = 2
////                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
//                    }
//                }
//            })
//        }
//        catch (e : Exception){
//            MENU_ERROR_MESSAGE = e.message.toString()
//            Log.d("debug menu", "ERROR $MENU_ERROR_MESSAGE")
////            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertLog(
        machineType: Boolean,
        machineClass: Boolean,
        machineStatus: Boolean,
        codeResponse: String,
        machineNumber: Int,
        machineStore: String,
        log: String,
    ){
        val current = LocalDateTime.now()

        val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = current.format(formatDay)

        val formatTime = DateTimeFormatter.ofPattern("HH:mm:ss")
        val timeNow = current.format(formatTime)

        val bodyUpdate = LogModel(
            date = date,
            time = timeNow,
            machineType = machineType,
            machineClass = machineClass,
            machineStatus = machineStatus,
            codeResponse = codeResponse,
            machineNumber = machineNumber,
            machineStore = machineStore,
            device = true,
            log = log
        )

        LogApp.CreateInstance().insertLog(bodyUpdate).enqueue(object :
            Callback<LogModel> {
            override fun onResponse(call: Call<LogModel>, response: Response<LogModel>) {
                if(response.code() == 201){
//                    navController.navigate(route = Screens.MenuOwner.route){
//                        popUpTo(Screens.MenuOwner.route) {
//                            inclusive = true
//                        }
//                    }
                }
            }

            override fun onFailure(call: Call<LogModel>, t: Throwable) {
//                Log.d("debug_transaction", t.message.toString())
                if (t.message == t.message){
//                    MENU_ERROR_MESSAGE = t.message.toString()
//                    BUTTON_MENU_EDIT = true
                }
            }
        })
    }
}