package com.example.alfaomega.api.machine

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.alfaomega.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MachineViewModel: ViewModel() {

    fun getMachine(){
        try {
            MachineApp.CreateInstance().fetchMachine(store = STORE_ID).enqueue(object :
                Callback<ArrayList<MachineModel>> {
                override fun onResponse(call: Call<ArrayList<MachineModel>>, response: Response<ArrayList<MachineModel>>) {
                    MENU_STATE = 0
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
                    Log.d("debug menu", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug menu", "Failed")
                        MACHINE_STATE = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            MACHINE_ERROR_MESSAGE = e.message.toString()
            Log.d("debug menu", "ERROR $MACHINE_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }
}