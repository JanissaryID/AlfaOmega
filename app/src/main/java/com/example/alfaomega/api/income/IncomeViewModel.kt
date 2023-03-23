package com.example.alfaomega.api.income

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.api.log.LogApp
import com.example.alfaomega.api.log.LogModel
import com.example.alfaomega.api.transaction.TransactionApp
import com.example.alfaomega.api.transaction.TransactionModel
import com.example.alfaomega.datapoint.DataPoints
import com.example.alfaomega.navigations.Screens
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class IncomeViewModel: ViewModel() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @ExperimentalCoroutinesApi
    fun CoroutineFetchIncomeOwner(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                INCOME_STATE = 0

                LIST_INCOME.clear()
                LIST_INCOME_FLOAT.clear()
                LIST_EXPENSES_FLOAT.clear()
                LIST_PROFIT_FLOAT.clear()

                INCOME_SUM = 0
                EXPENSES_SUM = 0
                PROFIT_SUM = 0

                fetchByOwner()
                delay(1500L)
//                Log.i("info_response", "Croutine End Income")
                if(INCOME_STATE != 1){
                    INCOME_STATE = 3
                }
            }
            catch (e: Exception){
//                INCOME_STATE = 3
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @ExperimentalCoroutinesApi
    fun CoroutineFetchIncomeStore(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
//                Log.i("info_response", "Croutine")
                INCOME_STATE_STORE = 0

                LIST_INCOME_STORE.clear()
                LIST_INCOME_FLOAT_STORE.clear()
                LIST_EXPENSES_FLOAT_STORE.clear()
                LIST_PROFIT_FLOAT_STORE.clear()

                INCOME_SUM_STORE = 0
                EXPENSES_SUM_STORE = 0
                PROFIT_SUM_STORE = 0

                fetchByStore()
                delay(1500L)
                if(INCOME_STATE_STORE != 1){
                    INCOME_STATE_STORE = 3
//                    Log.i("info_response", "Croutine End Income")
                }
            }
            catch (e: Exception){
//                INCOME_STATE = 3
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun fetchByOwner(){
//        Log.i("info_response", "Croutine runing Income")
        try {
            val current = LocalDateTime.now()

            val formatDay = DateTimeFormatter.ofPattern("MM-yyyy")
            val date = current.format(formatDay)

            var tempDate = 0
            var tempIncome = 0
            var tempExpenses = 0
            var tempNetProfit = 0
            var tempIndex = 0

            IncomeApp.CreateInstance().fetchIncomeByOwner(
                BearerToken = "Bearer " + TOKEN_API,
                date = date,
                owner = OWNER_ID
            ).enqueue(object :
                Callback<ArrayList<IncomeModel>> {
                override fun onResponse(call: Call<ArrayList<IncomeModel>>, response: Response<ArrayList<IncomeModel>>) {

                    Log.d("log_network", "Income Owner : ${response.code()} ${response.body()}")

                    if(response.code() == 200){
                        response.body()?.let {
                            LIST_INCOME = response.body()!!

                            LIST_INCOME.forEachIndexed(){ index, income ->
                                var myDate = income.date!!.subSequence(0, 2).toString().toInt()

                                if(myDate != tempDate){

                                    tempDate = myDate
                                    tempIncome = income.income!!.toInt()
                                    tempExpenses = income.outcome!!.toInt()
                                    tempNetProfit = income.income!!.toInt() - income.outcome!!.toInt()

                                    LIST_INCOME_FLOAT.add(DataPoint(myDate.toFloat(),tempIncome.toFloat()))
                                    LIST_EXPENSES_FLOAT.add(DataPoint(myDate.toFloat(), tempExpenses.toFloat()))
                                    LIST_PROFIT_FLOAT.add(DataPoint(myDate.toFloat(), tempNetProfit.toFloat()))

//                                    Log.d("get_log", "first = $tempIndex $tempIncome - $tempExpenses - $tempNetProfit")

                                    tempIndex++

                                }
                                else{
                                    tempDate = tempDate
                                    tempIncome += income.income!!.toInt()
                                    tempExpenses += income.outcome!!.toInt()
                                    tempNetProfit += income.income!!.toInt() - income.outcome!!.toInt()

                                    tempIndex--

                                    if(LIST_INCOME_FLOAT[tempIndex].x == myDate.toFloat()){
                                        LIST_INCOME_FLOAT.removeAt(tempIndex)
                                        LIST_EXPENSES_FLOAT.removeAt(tempIndex)
                                        LIST_PROFIT_FLOAT.removeAt(tempIndex)
                                    }

                                    LIST_INCOME_FLOAT.add(DataPoint(myDate.toFloat(),tempIncome.toFloat()))
                                    LIST_EXPENSES_FLOAT.add(DataPoint(myDate.toFloat(), tempExpenses.toFloat()))
                                    LIST_PROFIT_FLOAT.add(DataPoint(myDate.toFloat(), tempNetProfit.toFloat()))
//
//                                    Log.d("get_log", "two = $tempIndex  $tempIncome - $tempExpenses - $tempNetProfit")

                                    tempIndex++
                                }

                                INCOME_SUM += income.income!!.toInt()
                                EXPENSES_SUM += income.outcome!!.toInt()
                                PROFIT_SUM += income.income.toInt() - income.outcome.toInt()
                            }

//                            Log.d("get_log", "$LIST_INCOME_FLOAT")

                            if(!LIST_INCOME_FLOAT.isNullOrEmpty() &&
                                !LIST_PROFIT_FLOAT.isNullOrEmpty() &&
                                !LIST_EXPENSES_FLOAT.isNullOrEmpty() &&
                                !LIST_INCOME.isNullOrEmpty()
                            ){
                                INCOME_STATE = 1
                            }
                        }
                        if (LIST_INCOME_FLOAT.isNullOrEmpty() && INCOME_STATE == 0){
                            fetchByOwner()
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<IncomeModel>>, t: Throwable) {
//                    Log.d("debug menu", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
//                        Log.d("debug menu", "Failed")
                        INCOME_STATE = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            INCOME_ERROR_MESSAGE = e.message.toString()
//            Log.d("debug menu", "ERROR $LOG_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun fetchByStore(){
//        Log.i("info_response", "Croutine Running Income")
        try {
            val current = LocalDateTime.now()

            val formatDay = DateTimeFormatter.ofPattern("MM-yyyy")
            val date = current.format(formatDay)

            IncomeApp.CreateInstance().fetchIncomeByStore(
                BearerToken = "Bearer " + TOKEN_API,
                date = date,
                store = STORE_ID
            ).enqueue(object :
                Callback<ArrayList<IncomeModel>> {
                override fun onResponse(call: Call<ArrayList<IncomeModel>>, response: Response<ArrayList<IncomeModel>>) {

                    Log.d("log_network", "Income Store : ${response.code()} ${response.body()}")

                    if(response.code() == 200){
                        response.body()?.let {
//                            Log.d("get_log", "data Int = ${response}")
                            LIST_INCOME_STORE = response.body()!!

//                            Log.d("get_log", "${response.body()!!}")

                            LIST_INCOME_STORE.forEachIndexed{ index, income ->
                                var myDate = income.date!!.subSequence(0, 2).toString().toInt()

                                LIST_INCOME_FLOAT_STORE.add(DataPoint(myDate.toFloat(),income.income!!.toFloat()))
                                LIST_EXPENSES_FLOAT_STORE.add(DataPoint(myDate.toFloat(), income.outcome!!.toFloat()))
                                LIST_PROFIT_FLOAT_STORE.add(DataPoint(myDate.toFloat(), (income.income!!.toFloat() - income.outcome!!.toFloat())))
                                INCOME_SUM_STORE += income.income.toInt()
                                EXPENSES_SUM_STORE += income.outcome.toInt()
                                PROFIT_SUM_STORE += income.income.toInt() - income.outcome.toInt()
                            }

                            if(!LIST_INCOME_FLOAT_STORE.isNullOrEmpty() && !LIST_PROFIT_FLOAT_STORE.isNullOrEmpty()){
                                INCOME_STATE_STORE = 1
                            }
                        }
                        if (LIST_INCOME_FLOAT_STORE.isNullOrEmpty() && INCOME_STATE_STORE == 0){
                            fetchByStore()
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<IncomeModel>>, t: Throwable) {
//                    Log.d("debug menu", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
//                        Log.d("debug menu", "Failed")
                        INCOME_STATE_STORE = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            INCOME_ERROR_MESSAGE = e.message.toString()
//            Log.d("debug menu", "ERROR $LOG_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun fetchByStoreGetNull(
        incomeStat: Boolean,
        income: String = "0",
        expenses: String = "0",
    ){
        try {
            val current = LocalDateTime.now()

            val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val date = current.format(formatDay)

            var incomeStore = ""
            var expensesStore = ""
            var idIncome = ""

            IncomeApp.CreateInstance().fetchIncomeByStoreNotNUll(
                BearerToken = "Bearer " + TOKEN_API,
                date = date,
                store = STORE_ID
            ).enqueue(object :
                Callback<ArrayList<IncomeModel>> {
                override fun onResponse(call: Call<ArrayList<IncomeModel>>, response: Response<ArrayList<IncomeModel>>) {
                    INCOME_STATE_STORE = 0

                    if(response.code() == 200){
                        response.body()?.let {
//                            Log.d("get_log", "data Int = ${response}")
                            LIST_INCOME_STORE = response.body()!!

//                            Log.d("get_log", "Get Income Store = $LIST_INCOME_STORE")
                        }

                        if (LIST_INCOME_STORE.isNullOrEmpty()){
                            if (incomeStat){
                                insertIncome(income = income, expenses = expenses)
                            }
                            else{
                                insertExpenses(income = income, expenses = expenses)
                            }
                        }
                        else{

                            incomeStore = response.body()!![0].income.toString()
                            expensesStore = response.body()!![0].outcome.toString()
                            idIncome = response.body()!![0].id.toString()

//                            Log.d("get_log", "Get Income = $incomeStore  --  $idIncome")

                            if (incomeStat){
                                updateIncome(income = (income.toInt() + incomeStore.toInt()).toString(), idIncome = idIncome)
                            }
                            else{
                                updateExpenses(expenses = (expenses.toInt() + expensesStore.toInt()).toString(), idIncome = idIncome)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<IncomeModel>>, t: Throwable) {
//                    Log.d("debug menu", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
//                        Log.d("debug menu", "Failed")
                        INCOME_STATE_STORE = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            INCOME_ERROR_MESSAGE = e.message.toString()
//            Log.d("debug menu", "ERROR $LOG_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun insertExpenses(
        expenses: String,
        income: String
    ){
        val current = LocalDateTime.now()

        val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = current.format(formatDay)


        val bodyUpdate = IncomeModel(
            date = date,
            ownerId = OWNER_ID,
            storeId = STORE_ID,
            outcome = expenses,
            income = income
        )

        IncomeApp.CreateInstance().insertIncome(
            BearerToken = "Bearer " + TOKEN_API,
            bodyUpdate
        ).enqueue(object :
            Callback<IncomeModel> {
            override fun onResponse(call: Call<IncomeModel>, response: Response<IncomeModel>) {
//                Log.d("debug", "Code Insert Transaction ${response}")
                Log.i("info_response", "Response Insert Income : ${response}")
            }

            override fun onFailure(call: Call<IncomeModel>, t: Throwable) {
//                Log.d("debug_transaction", t.message.toString())
                if (t.message == t.message){
                    TRANSACTION_ERROR = t.message.toString()
                    NEW_TRANSACATION_BUTTON = true
                }
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun insertIncome(
        expenses: String,
        income: String
    ){
        val current = LocalDateTime.now()

        val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = current.format(formatDay)


        val bodyUpdate = IncomeModel(
            date = date,
            ownerId = OWNER_ID,
            storeId = STORE_ID,
            outcome = expenses,
            income = income
        )

        IncomeApp.CreateInstance().insertIncome(
            BearerToken = "Bearer " + TOKEN_API,
            bodyUpdate
        ).enqueue(object :
            Callback<IncomeModel> {
            override fun onResponse(call: Call<IncomeModel>, response: Response<IncomeModel>) {
//                Log.d("debug", "Code Insert Transaction ${response}")
                Log.i("info_response", "Response Insert Income : ${response}")
            }

            override fun onFailure(call: Call<IncomeModel>, t: Throwable) {
//                Log.d("debug_transaction", t.message.toString())
                if (t.message == t.message){
                    TRANSACTION_ERROR = t.message.toString()
                    NEW_TRANSACATION_BUTTON = true
                }
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun updateExpenses(
        expenses: String,
        idIncome: String
    ){
        val current = LocalDateTime.now()

        val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = current.format(formatDay)


        val bodyUpdate = IncomeModel(
            outcome = expenses
        )

        IncomeApp.CreateInstance().updateIncome(
            BearerToken = "Bearer " + TOKEN_API,
            id = idIncome,
            bodyUpdate
        ).enqueue(object :
            Callback<IncomeModel> {
            override fun onResponse(call: Call<IncomeModel>, response: Response<IncomeModel>) {
//                Log.d("debug", "Code Insert Transaction ${response}")
                Log.i("info_response", "Response Insert Expenses : ${response}")
            }

            override fun onFailure(call: Call<IncomeModel>, t: Throwable) {
//                Log.d("debug_transaction", t.message.toString())
                if (t.message == t.message){
                    TRANSACTION_ERROR = t.message.toString()
                    NEW_TRANSACATION_BUTTON = true
                }
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun updateIncome(
        idIncome: String,
        income: String
    ){
        val current = LocalDateTime.now()

        val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = current.format(formatDay)


        val bodyUpdate = IncomeModel(
            income = income
        )

        IncomeApp.CreateInstance().updateIncome(
            BearerToken = "Bearer " + TOKEN_API,
            id = idIncome,
            bodyUpdate
        ).enqueue(object :
            Callback<IncomeModel> {
            override fun onResponse(call: Call<IncomeModel>, response: Response<IncomeModel>) {
//                Log.d("debug", "Code Insert Transaction ${response}")
                Log.i("info_response", "Response Insert Income : ${response}")
            }

            override fun onFailure(call: Call<IncomeModel>, t: Throwable) {
//                Log.d("debug_transaction", t.message.toString())
                if (t.message == t.message){
                    TRANSACTION_ERROR = t.message.toString()
                    NEW_TRANSACATION_BUTTON = true
                }
            }
        })
    }
}