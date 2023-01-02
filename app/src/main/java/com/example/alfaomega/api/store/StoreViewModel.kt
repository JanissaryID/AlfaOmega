package com.example.alfaomega.api.store

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.alfaomega.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreViewModel : ViewModel(){
    fun FetchStore(){
        try {
            StoreApp.CreateInstance().fetchStore(
                OwnerId = OWNER_ID
            ).enqueue(object :
                Callback<ArrayList<StoreModel>> {
                override fun onResponse(call: Call<ArrayList<StoreModel>>, response: Response<ArrayList<StoreModel>>) {

                    STORE_STATE = 0

                    if(response.code() == 200){
                        response.body()?.let {
                            STORE_LIST_RESPONSE = response.body()!!

                            STORE_STATE = 1
//                            Log.i("i`nfo_response", "Data GIANT : ${MENU_LIST_GIANT_RESPONSE}")
                        }
                        if (STORE_LIST_RESPONSE.isNullOrEmpty()){
                            STORE_STATE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<StoreModel>>, t: Throwable) {
                    Log.d("debug_store", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug_store", "Failed")
                        STORE_STATE = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            STORE_ERROR_MESSAGE = e.message.toString()
            Log.d("debug_store", "ERROR $STORE_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    fun GetStore(){
        try {
            StoreApp.CreateInstance().getStore(id = STORE_ID).enqueue(object :
                Callback<ArrayList<StoreModel>> {
                override fun onResponse(call: Call<ArrayList<StoreModel>>, response: Response<ArrayList<StoreModel>>) {

                    STORE_STATE = 0

                    if(response.code() == 200){
                        response.body()?.let {

                            STORE_NAME = response.body()!![0].name!!
                            STORE_CITY = response.body()!![0].city!!
                            STORE_ADDRESS = response.body()!![0].address!!

                            STORE_STATE = 1
//                            Log.i("info_response", "Data Store : ${response.body()!!}")
                        }
                        if (STORE_LIST_RESPONSE.isNullOrEmpty()){
                            STORE_STATE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<StoreModel>>, t: Throwable) {
                    Log.d("debug_store", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug_store", "Failed")
                        STORE_STATE = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            STORE_ERROR_MESSAGE = e.message.toString()
            Log.d("debug_store", "ERROR $STORE_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }
}