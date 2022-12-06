package com.example.alfaomega.api.menu

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.alfaomega.*
import com.example.alfaomega.proto.ProtoViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuViewModel: ViewModel() {

    fun getMenu(){
        try {
            MenuApp.CreateInstance().fetchMenu(store = STORE_ID).enqueue(object :
                Callback<ArrayList<MenuModel>> {
                override fun onResponse(call: Call<ArrayList<MenuModel>>, response: Response<ArrayList<MenuModel>>) {
                    MENU_STATE = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            MENU_LIST_GIANT_RESPONSE = response.body()!!.filter { menu -> menu.menuClass == false } as ArrayList<MenuModel>
                            MENU_LIST_TITAN_RESPONSE = response.body()!!.filter { menu -> menu.menuClass == true } as ArrayList<MenuModel>

                            MENU_STATE = 1
//                            Log.i("i`nfo_response", "Data GIANT : ${MENU_LIST_GIANT_RESPONSE}")
                        }
                        if (MENU_LIST_GIANT_RESPONSE.isNullOrEmpty()){
                            MENU_STATE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<MenuModel>>, t: Throwable) {
                    Log.d("debug menu", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug menu", "Failed")
                        MENU_STATE = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            MENU_ERROR_MESSAGE = e.message.toString()
            Log.d("debug menu", "ERROR $MENU_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    fun getTime(){
        try {
            MenuApp.CreateInstance().fetchMenu(store = STORE_ID).enqueue(object :
                Callback<ArrayList<MenuModel>> {
                override fun onResponse(call: Call<ArrayList<MenuModel>>, response: Response<ArrayList<MenuModel>>) {
                    MENU_STATE = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            var WashTimeGiant: ArrayList<MenuModel> =  response.body()!!.filter { menu -> menu.menuClass == false && menu.isWasher == true && menu.isDryer == false} as ArrayList<MenuModel>
                            var DryTimeGiant: ArrayList<MenuModel> =  response.body()!!.filter { menu -> menu.menuClass == false && menu.isWasher == false && menu.isDryer == true} as ArrayList<MenuModel>
                            var WashTimeTitan: ArrayList<MenuModel> =  response.body()!!.filter { menu -> menu.menuClass == true && menu.isWasher == true && menu.isDryer == false} as ArrayList<MenuModel>
                            var DryTimeTitan: ArrayList<MenuModel> =  response.body()!!.filter { menu -> menu.menuClass == true && menu.isWasher == false && menu.isDryer == true} as ArrayList<MenuModel>

                            Log.i("info_response", "TIME 2 : ${WashTimeGiant[0].menuTime!!.toInt()}  ${DryTimeGiant[0].menuTime!!.toInt()}  ${WashTimeTitan[0].menuTime!!.toInt()}  ${DryTimeTitan[0].menuTime!!.toInt()}")

                            TIME_WASHER_GIANT = WashTimeGiant[0].menuTime!!.toInt()
                            TIME_DRYER_GIANT = DryTimeGiant[0].menuTime!!.toInt()
                            TIME_WASHER_TITAN = WashTimeTitan[0].menuTime!!.toInt()
                            TIME_DRYER_TITAN = DryTimeTitan[0].menuTime!!.toInt()

//                            protoViewModel.updateWashTimeGiant(1)
//                            protoViewModel.updateDryTimeGiant(2)
//                            protoViewModel.updateWashTimeTitan(3)
//                            protoViewModel.updateDryTimeTitan(4)
                            MENU_STATE = 1
//                            Log.i("i`nfo_response", "Data GIANT : ${MENU_LIST_GIANT_RESPONSE}")
                        }
                        if (MENU_LIST_GIANT_RESPONSE.isNullOrEmpty()){
                            MENU_STATE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<MenuModel>>, t: Throwable) {
                    Log.d("debug menu", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug menu", "Failed")
                        MENU_STATE = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            MENU_ERROR_MESSAGE = e.message.toString()
            Log.d("debug menu", "ERROR $MENU_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }
}