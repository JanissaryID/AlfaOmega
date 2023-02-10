package com.example.alfaomega.api.store

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.api.user.UserApp
import com.example.alfaomega.api.user.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreViewModel : ViewModel(){
    fun FetchStore(){
        try {
            StoreApp.CreateInstance().fetchStore(
                BearerToken = "Bearer " + TOKEN_API,
                OwnerId = OWNER_ID
            ).enqueue(object :
                Callback<ArrayList<StoreModel>> {
                override fun onResponse(call: Call<ArrayList<StoreModel>>, response: Response<ArrayList<StoreModel>>) {

                    STORE_STATE = 0

                    STORE_LIST_RESPONSE.clear()

                    if(response.code() == 200){
                        response.body()?.let {
                            STORE_LIST_RESPONSE = response.body()!!

                            STORE_STATE = 1
//                            Log.i("info_response", "Store = $STORE_LIST_RESPONSE")
                        }
                        if (STORE_LIST_RESPONSE.isNullOrEmpty()){
                            STORE_STATE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<StoreModel>>, t: Throwable) {
//                    Log.d("debug_store", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
//                        Log.d("debug_store", "Failed")
                        STORE_STATE = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            STORE_ERROR_MESSAGE = e.message.toString()
//            Log.d("debug_store", "ERROR $STORE_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    fun GetStore(){
        try {
            StoreApp.CreateInstance().getStore(
                BearerToken = "Bearer " + TOKEN_API,
                id = STORE_ID).enqueue(object :
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
//                    Log.d("debug_store", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
//                        Log.d("debug_store", "Failed")
                        STORE_STATE = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            STORE_ERROR_MESSAGE = e.message.toString()
//            Log.d("debug_store", "ERROR $STORE_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun insertStore(
        nameStore: String,
        addressStore: String,
        cityStore: String,
        ownerID: String,
        ScreenDestination: String,
        navController: NavController
    ){
        val bodyUpdate = StoreModel(
            name = nameStore,
            address = addressStore,
            city = cityStore,
            ownerId = ownerID
        )

        StoreApp.CreateInstance().insertStore(
            BearerToken = "Bearer " + TOKEN_API,
            bodyUpdate).enqueue(object :
            Callback<StoreModel> {
            override fun onResponse(call: Call<StoreModel>, response: Response<StoreModel>) {
//                Log.d("debug_user", response.toString())
                if(response.code() == 201){
                    navController.navigate(route = ScreenDestination){
                        popUpTo(ScreenDestination) {
                            inclusive = true
                        }
                    }
                }
            }

            override fun onFailure(call: Call<StoreModel>, t: Throwable) {
//                Log.d("debug_transaction", t.message.toString())
                if (t.message == t.message){
                    USER_ERROR_MESSAGE = t.message.toString()
//                    BUTTON_MENU_EDIT = true
                }
            }
        })
    }

    fun updateStore(
        nameStore: String,
        addressStore: String,
        cityStore: String,
        ownerID: String,
        ScreenDestination: String,
        navController: NavController
    ){
        val bodyUpdate = StoreModel(
            name = nameStore,
            address = addressStore,
            city = cityStore,
            ownerId = ownerID
        )

        try {
            StoreApp.CreateInstance().updateStore(
                BearerToken = "Bearer " + TOKEN_API,
                id = ownerID,
                bodyUpdate).enqueue(object :
                Callback<StoreModel> {
                override fun onResponse(call: Call<StoreModel>, response: Response<StoreModel>) {
                    if(response.code() == 200){
                        val responseBodyData = response.body()
                        if (!responseBodyData!!.id.isNullOrEmpty()){

                            BUTTON_USER_EDIT = true

                            navController.navigate(route = ScreenDestination){
                                popUpTo(ScreenDestination) {
                                    inclusive = true
                                }
                            }
                        }
                        else{
                            updateStore(
                                nameStore = nameStore,
                                addressStore = addressStore,
                                cityStore = cityStore,
                                ownerID = ownerID,
                                ScreenDestination = ScreenDestination,
                                navController = navController
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<StoreModel>, t: Throwable) {
//                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            USER_ERROR_MESSAGE = e.message.toString()
//            Log.d("debug", "ERROR $USER_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }
}