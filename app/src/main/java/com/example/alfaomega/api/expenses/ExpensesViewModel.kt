package com.example.alfaomega.api.expenses

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.alfaomega.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ExpensesViewModel: ViewModel() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun fetchByOwner(){
        try {
            val current = LocalDateTime.now()

            val formatDay = DateTimeFormatter.ofPattern("MM-yyyy")
            val date = current.format(formatDay)

            ExpensesApp.CreateInstance().fetchExpensesByOwner(
                BearerToken = "Bearer " + TOKEN_API,
                date = date,
                owner = OWNER_ID
            ).enqueue(object :
                Callback<ArrayList<ExpensesModel>> {
                override fun onResponse(call: Call<ArrayList<ExpensesModel>>, response: Response<ArrayList<ExpensesModel>>) {
                    EXPENSES_STATE = 0

                    LIST_EXPENSES.clear()

                    if(response.code() == 200){
                        response.body()?.let {
                            LIST_EXPENSES = response.body()!!

                            if(!LIST_EXPENSES.isNullOrEmpty()
                            ){
                                EXPENSES_STATE = 1
                            }
                        }
                        if (LIST_INCOME_FLOAT.isNullOrEmpty()){
                            EXPENSES_STATE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<ExpensesModel>>, t: Throwable) {
//                    Log.d("debug menu", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
//                        Log.d("debug menu", "Failed")
                        EXPENSES_STATE = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            EXPENSES_ERROR_MESSAGE = e.message.toString()
//            Log.d("debug menu", "ERROR $LOG_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun fetchByStore(){
        try {
            val current = LocalDateTime.now()

            val formatDay = DateTimeFormatter.ofPattern("MM-yyyy")
            val dayNow = current.format(formatDay)
            val date = if(!DATE_PICK.isNullOrEmpty()) DATE_PICK.substring(startIndex = 3) else dayNow

//            Log.d("get_log", "${date} -- ${dayNow}")

            ExpensesApp.CreateInstance().fetchExpensesByStore(
                BearerToken = "Bearer " + TOKEN_API,
                date =  date,
                store = STORE_ID
            ).enqueue(object :
                Callback<ArrayList<ExpensesModel>> {
                override fun onResponse(call: Call<ArrayList<ExpensesModel>>, response: Response<ArrayList<ExpensesModel>>) {
                    EXPENSES_STATE_STORE = 0

                    LIST_EXPENSES_STORE.clear()
//                    Log.d("get_log", "${response}")

                    if(response.code() == 200){
                        response.body()?.let {
                            LIST_EXPENSES_STORE = response.body()!!

//                            Log.d("get_log", "${response.body()!!}")

                            if(!LIST_EXPENSES_STORE.isNullOrEmpty()){
                                EXPENSES_STATE_STORE = 1
                            }
                        }
                        if (LIST_EXPENSES_STORE.isNullOrEmpty()){
                            EXPENSES_STATE_STORE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<ExpensesModel>>, t: Throwable) {
                    if (t.message == t.message){
                        EXPENSES_STATE_STORE = 2
                    }
                }
            })
        }
        catch (e : Exception){
            EXPENSES_ERROR_MESSAGE = e.message.toString()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun insertExpenses(
        expenses: String,
        note: String
    ){
        val current = LocalDateTime.now()

        val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = current.format(formatDay)


        val bodyUpdate = ExpensesModel(
            date = date,
            ownerid = OWNER_ID,
            storeid = STORE_ID,
            expenses = expenses,
            notes = note,
            admin = USER_NAME
        )

        ExpensesApp.CreateInstance().insertExpenses(
            BearerToken = "Bearer " + TOKEN_API,
            bodyUpdate
        ).enqueue(object :
            Callback<ExpensesModel> {
            override fun onResponse(call: Call<ExpensesModel>, response: Response<ExpensesModel>) {
//                Log.d("debug", "Code Insert Transaction ${response}")
                Log.i("info_response", "Response Insert Income : ${response}")
            }

            override fun onFailure(call: Call<ExpensesModel>, t: Throwable) {
//                Log.d("debug_transaction", t.message.toString())
                if (t.message == t.message){
                    TRANSACTION_ERROR = t.message.toString()
                    NEW_TRANSACATION_BUTTON = true
                }
            }
        })
    }
}