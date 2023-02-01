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
import com.example.alfaomega.*
import com.example.alfaomega.api.log.LogApp
import com.example.alfaomega.api.log.LogModel
import com.example.alfaomega.datapoint.DataPoints
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class IncomeViewModel: ViewModel() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun fetchByOwner(){
        try {
            val current = LocalDateTime.now()

            val formatDay = DateTimeFormatter.ofPattern("MM-yyyy")
            val date = current.format(formatDay)

            IncomeApp.CreateInstance().fetchIncomeByOwner(
                BearerToken = "Bearer " + TOKEN_API,
                date = "01-2023",
                owner = OWNER_ID
            ).enqueue(object :
                Callback<ArrayList<IncomeModel>> {
                override fun onResponse(call: Call<ArrayList<IncomeModel>>, response: Response<ArrayList<IncomeModel>>) {
                    INCOME_STATE = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            LIST_INCOME = response.body()!!
                            LIST_INCOME.forEachIndexed(){ index, income ->
                                LIST_INCOME_FLOAT.add(DataPoint(index.toFloat(),income.income!!.toFloat()))
                                LIST_EXPENSES_FLOAT.add(DataPoint(index.toFloat(), income.outcome!!.toFloat()))
                                LIST_PROFIT_FLOAT.add(DataPoint(index.toFloat(), (income.income!!.toFloat() - income.outcome!!.toFloat())))
                            }
//                            LIST_INCOME_INT.add(response.body()!!.get(index = 2).income!!.toInt())

                            if(!LIST_INCOME_FLOAT.isNullOrEmpty() && !LIST_PROFIT_FLOAT.isNullOrEmpty()){
                                INCOME_STATE = 1
//                                Log.d("get_log", "data Int = ${LIST_INCOME}")
//                                Log.d("get_log", "data Int = ${LIST_INCOME_FLOAT}")
//                                Log.d("get_log", "data Int = ${LIST_EXPENSES_FLOAT}")
//                                Log.d("get_log", "data Int = ${LIST_PROFIT_FLOAT}")
                            }
                        }
                        if (LIST_INCOME_FLOAT.isNullOrEmpty()){
                            INCOME_STATE = 3
//                            fetchByOwner()
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
        try {
            val current = LocalDateTime.now()

            val formatDay = DateTimeFormatter.ofPattern("MM-yyyy")
            val date = current.format(formatDay)

            IncomeApp.CreateInstance().fetchIncomeByStore(
                BearerToken = "Bearer " + TOKEN_API,
                date = "01-2023",
                store = STORE_ID
            ).enqueue(object :
                Callback<ArrayList<IncomeModel>> {
                override fun onResponse(call: Call<ArrayList<IncomeModel>>, response: Response<ArrayList<IncomeModel>>) {
                    INCOME_STATE_STORE = 0
                    if(response.code() == 200){
                        response.body()?.let {
//                            Log.d("get_log", "data Int = ${response}")
                            LIST_INCOME_STORE = response.body()!!
                            LIST_INCOME_STORE.forEachIndexed{ index, income ->
                                LIST_INCOME_FLOAT_STORE.add(DataPoint(index.toFloat(),income.income!!.toFloat()))
                                LIST_EXPENSES_FLOAT_STORE.add(DataPoint(index.toFloat(), income.outcome!!.toFloat()))
                                LIST_PROFIT_FLOAT_STORE.add(DataPoint(index.toFloat(), (income.income!!.toFloat() - income.outcome!!.toFloat())))
                            }

                            if(!LIST_INCOME_FLOAT_STORE.isNullOrEmpty() && !LIST_PROFIT_FLOAT_STORE.isNullOrEmpty()){
                                INCOME_STATE_STORE = 1
//                                Log.d("get_log", "data Int = ${LIST_INCOME_STORE}")
//                                Log.d("get_log", "data Int = ${LIST_INCOME_FLOAT_STORE}")
//                                Log.d("get_log", "data Int = ${LIST_EXPENSES_FLOAT_STORE}")
//                                Log.d("get_log", "data Int = ${LIST_PROFIT_FLOAT_STORE}")
                            }
//                            Log.d("get_log", "$LIST_LOG")
                        }
                        if (LIST_INCOME_FLOAT_STORE.isNullOrEmpty()){
                            INCOME_STATE_STORE = 3
                            fetchByOwner()
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
}