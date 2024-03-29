package com.example.alfaomega.api.qr

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.api.problem.ProblemModel
import com.example.alfaomega.navigations.Screens
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QrViewModel: ViewModel() {
    fun fetchQrOwner(){
        try {
            QR_STATE = 0

            QR_DATA = QrModel()

            QrApp.CreateInstance().fetchQr(
                BearerToken = "Bearer " + TOKEN_API,
                owner = OWNER_ID,
            ).enqueue(object :
                Callback<ArrayList<QrModel>>{
                override fun onResponse(call: Call<ArrayList<QrModel>>, response: Response<ArrayList<QrModel>>) {

                    Log.d("log_network", "Qr : ${response.code()} ${response.body()}")

                    if(response.code() == 200){
                        response.body()?.let {
                            if(response.body()!!.isNullOrEmpty()){
                                QR_DATA = QrModel()
                            }
                            else{
                                QR_DATA = response.body()!![0]
                            }

                            QR_STATE = 1
//                            Log.d("get_log", "$LIST_PROBLEM_MACHINE")
                        }
                        if (LIST_PROBLEM_MACHINE.isNullOrEmpty()){
                            QR_STATE = 3
//                            fetchProblem()
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<QrModel>>, t: Throwable) {
                    Log.d("get_qr", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
//                        Log.d("debug menu", "Failed")
                        QR_STATE = 2
                    }
                }
            })
        }
        catch (e : Exception){
            QR_ERROR_MESSAGE = e.message.toString()
            Log.d("get_qr", "Fail get Data 1 ${QR_ERROR_MESSAGE}")
//            Log.d("debug menu", "ERROR $PROBLEM_MACHINE_ERROR_MESSAGE")
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun insertQR(
        merchantID: String,
        clientID: String,
        KeyID: String,
        ownerID: String,
        navController: NavController
    ){
        val bodyUpdate = QrModel(
            merchantId = merchantID,
            clientId = clientID,
            keyId = KeyID,
            ownerid = ownerID
        )

        QrApp.CreateInstance().insertQr(
            BearerToken = "Bearer " + TOKEN_API,
            bodyUpdate).enqueue(object :
            Callback<QrModel> {
            override fun onResponse(call: Call<QrModel>, response: Response<QrModel>) {
//                Log.d("log_qr", "$response")
                if(response.code() == 201){
                    navController.navigate(route = Screens.Home.route){
                        popUpTo(Screens.Home.route) {
                            inclusive = true
                        }
                    }
                    fetchQrOwner()
                }
                else{
                    insertQR(
                        merchantID = merchantID,
                        clientID = clientID,
                        KeyID = KeyID,
                        ownerID = ownerID,
                        navController = navController
                    )
                }
            }

            override fun onFailure(call: Call<QrModel>, t: Throwable) {
                if (t.message == t.message){
                    PROBLEM_MACHINE_ERROR_MESSAGE = t.message.toString()
//                    BUTTON_MENU_EDIT = true
                }
            }
        })
    }

    fun updateQr(
        idQr: String,
        merchantID: String,
        clientID: String,
        KeyID: String,
        navController: NavController
    ){
        val bodyDataUpdate = QrModel(
            merchantId = merchantID,
            clientId = clientID,
            keyId = KeyID,
        )

        try {
            QrApp.CreateInstance().updateQr(
                BearerToken = "Bearer " + TOKEN_API,
                id = idQr,
                bodyDataUpdate
            ).enqueue(object :
                Callback<QrModel> {
                @RequiresApi(Build.VERSION_CODES.TIRAMISU)
                override fun onResponse(call: Call<QrModel>, response: Response<QrModel>) {
                    Log.d("get_qr", "$response")
                    if(response.code() == 200){
//                        Log.d("debug", "Body Update Machine ${response.body()}")
                        navController.navigate(route = Screens.Home.route){
                            popUpTo(Screens.Home.route) {
                                inclusive = true
                            }
                        }
                        fetchQrOwner()
                    }
                    else{
                        updateQr(
                            idQr = idQr,
                            merchantID = merchantID,
                            clientID = clientID,
                            KeyID = KeyID,
                            navController = navController
                        )
                    }
                }

                override fun onFailure(call: Call<QrModel>, t: Throwable) {
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