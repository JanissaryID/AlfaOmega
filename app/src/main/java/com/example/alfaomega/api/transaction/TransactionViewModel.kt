package com.example.alfaomega.api.transaction

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.navigations.Screens
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TransactionViewModel: ViewModel() {

    fun getTransactionActive() {
        try {
            TransactionApp.CreateInstance().fetchTransactionActive(store = STORE_ID).enqueue(object :
                Callback<ArrayList<TransactionModel>> {
                override fun onResponse(
                    call: Call<ArrayList<TransactionModel>>,
                    response: Response<ArrayList<TransactionModel>>
                ) {
                    TRANSACTION_ACTIVE_STATE = 0
                    if (response.code() == 200) {
                        response.body()?.let {
                            TRANSACTION_ACTIVE_RESPONSE = response.body()!!
                            TRANSACTION_ACTIVE_STATE = 1
//                            Log.i("info_response", "Data GIANT : ${MENU_LIST_GIANT_RESPONSE}")
                        }
                        if (TRANSACTION_ACTIVE_RESPONSE.isNullOrEmpty()) {
                            TRANSACTION_ACTIVE_STATE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<TransactionModel>>, t: Throwable) {
                    Log.d("debug menu", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message) {
                        Log.d("debug menu", "Failed")
                        TRANSACTION_ACTIVE_STATE = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            MENU_ERROR_MESSAGE = e.message.toString()
            Log.d("debug menu", "ERROR $MENU_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertTransaction(
        transactionClass: Boolean,
        transactionMenu: String,
        transactionPrice: String,
        transactionPayment: Boolean,
        transactionCustomer: String,
        transactionStateMachine: Int,
        isWasher: Boolean,
        isDryer: Boolean,
        navController: NavController,
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
            isDryer = isDryer
        )

        TransactionApp.CreateInstance().insertTransactions(bodyUpdate).enqueue(object :
            Callback<TransactionModel> {
            override fun onResponse(call: Call<TransactionModel>, response: Response<TransactionModel>) {
//                Log.d("debug", "Code Insert Transaction ${response}")
                Log.i("info_response", "Response Insert Transaction : ${response}")
                if(response.code() == 201){
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
                Log.d("error", t.message.toString())
                if (t.message == t.message){

                    NEW_TRANSACATION_BUTTON = true

//                    Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}