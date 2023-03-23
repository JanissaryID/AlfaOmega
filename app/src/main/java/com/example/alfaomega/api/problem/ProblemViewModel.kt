package com.example.alfaomega.api.problem

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.alfaomega.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ProblemViewModel: ViewModel() {
    fun fetchProblem(){
        try {
            ProblemApp.CreateInstance().fetchProblemMachine(
                BearerToken = "Bearer " + TOKEN_API,
                store = STORE_ID,
                idMachine = MACHINE_ID
            ).enqueue(object :
                Callback<ArrayList<ProblemModel>> {
                override fun onResponse(call: Call<ArrayList<ProblemModel>>, response: Response<ArrayList<ProblemModel>>) {
                    PROBLEM_MACHINE_STATE = 0

                    LIST_PROBLEM_MACHINE.clear()

                    Log.d("log_network", "Problem : ${response.code()} ${response.body()}")
//                    Log.d("get_log", "$response")
                    if(response.code() == 200){
                        response.body()?.let {
                            LIST_PROBLEM_MACHINE = response.body()!!

                            PROBLEM_MACHINE_STATE = 1
//                            Log.d("get_log", "$LIST_PROBLEM_MACHINE")
                        }
                        if (LIST_PROBLEM_MACHINE.isNullOrEmpty()){
                            PROBLEM_MACHINE_STATE = 3
//                            fetchProblem()
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<ProblemModel>>, t: Throwable) {
//                    Log.d("debug menu", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
//                        Log.d("debug menu", "Failed")
                        PROBLEM_MACHINE_STATE = 2
                    }
                }
            })
        }
        catch (e : Exception){
            PROBLEM_MACHINE_ERROR_MESSAGE = e.message.toString()
//            Log.d("debug menu", "ERROR $PROBLEM_MACHINE_ERROR_MESSAGE")
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun insertProblem(
        admin: String,
        store: String,
        numberMachine: Int,
        problem: String,
        date: String,
        idMachine: String,
        storeName: String,
        navController: NavController
    ){
        val bodyUpdate = ProblemModel(
            admin = admin,
            machineNumber = numberMachine,
            idStore = store,
            isSolved = false,
            date = date,
            problem = problem,
            idMachine = idMachine,
            problemStore = storeName
        )

        ProblemApp.CreateInstance().insertProblemMachine(
            BearerToken = "Bearer " + TOKEN_API,
            bodyUpdate).enqueue(object :
            Callback<ProblemModel> {
            override fun onResponse(call: Call<ProblemModel>, response: Response<ProblemModel>) {
                if(response.code() == 201){
//                    navController.navigate(route = Screens.UserOwner.route){
//                        popUpTo(Screens.UserOwner.route) {
//                            inclusive = true
//                        }
//                    }
                }
            }

            override fun onFailure(call: Call<ProblemModel>, t: Throwable) {
                if (t.message == t.message){
                    PROBLEM_MACHINE_ERROR_MESSAGE = t.message.toString()
//                    BUTTON_MENU_EDIT = true
                }
            }
        })
    }

    fun updateProblem(idProblem: String){
        val bodyDataUpdate = ProblemModel(
            isSolved = true
        )

        try {
            ProblemApp.CreateInstance().updateProblemMachine(
                BearerToken = "Bearer " + TOKEN_API,
                id = idProblem,
                bodyDataUpdate
            ).enqueue(object :
                Callback<ProblemModel> {
                @RequiresApi(Build.VERSION_CODES.TIRAMISU)
                override fun onResponse(call: Call<ProblemModel>, response: Response<ProblemModel>) {
//                    Log.d("debug", "Code Update Machine ${response}")
                    if(response.code() == 200){
//                        Log.d("debug", "Body Update Machine ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<ProblemModel>, t: Throwable) {
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