package com.example.alfaomega.api.menu

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.alfaomega.MENU_ERROR_MESSAGE
import com.example.alfaomega.MENU_LIST_RESPONSE
import com.example.alfaomega.MENU_STATE
import com.example.alfaomega.STORE_ID
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
                            MENU_LIST_RESPONSE = response.body()!!
                            MENU_STATE = 1
                            Log.i("info_response", "Data : ${MENU_LIST_RESPONSE}")
                        }
                        if (MENU_LIST_RESPONSE.isNullOrEmpty()){
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