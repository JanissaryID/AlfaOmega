package com.example.alfaomega.api.rules

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.navigations.Screens
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RuleViewModel : ViewModel() {

    fun getRules(){
        try {
            RuleApp.CreateInstance().fetchRule(
                BearerToken = "Bearer " + TOKEN_API,
                OwnerId = OWNER_ID
            ).enqueue(object :
                Callback<ArrayList<RuleModel>> {
                override fun onResponse(call: Call<ArrayList<RuleModel>>, response: Response<ArrayList<RuleModel>>) {
                    RULE_STATE = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            LIST_RULE = response.body()!!
                            Log.i("info_response", "Body : ${response.body()}")
                            RULE_STATE = 1
                        }
                        if (LIST_RULE.isNullOrEmpty()){
                            RULE_STATE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<RuleModel>>, t: Throwable) {
                    if (t.message == t.message){
                        RULE_STATE = 2
                    }
                }
            })
        }
        catch (e : Exception){
            RULE_ERROR_MESSAGE = e.message.toString()
            Log.d("debug menu", "ERROR $RULE_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    fun updateRule(
        idRule: String,
        ruleText: String,
        navController: NavController
    ){
        val bodyDataUpdate = RuleModel(
            rule = ruleText
        )

        try {
            RuleApp.CreateInstance().updateRule(
                BearerToken = "Bearer " + TOKEN_API,
                id = idRule,
                bodyDataUpdate).enqueue(object :
                Callback<RuleModel> {
                override fun onResponse(call: Call<RuleModel>, response: Response<RuleModel>) {
                    if(response.code() == 200){
                        val responseBodyData = response.body()
                        if (!responseBodyData!!.id.isNullOrEmpty()){

                            BUTTON_RULE_EDIT = true

                            navController.navigate(route = Screens.RulesOwner.route){
                                popUpTo(Screens.RulesOwner.route) {
                                    inclusive = true
                                }
                            }
                        }
                        else{
                            updateRule(
                                idRule = idRule,
                                ruleText = ruleText,
                                navController = navController
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<RuleModel>, t: Throwable) {
                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            RULE_ERROR_MESSAGE = e.message.toString()
            Log.d("debug", "ERROR $RULE_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun insertRule(
        ruleText: String,
        navController: NavController
    ){
        val bodyUpdate = RuleModel(
            rule = ruleText
        )

        RuleApp.CreateInstance().insertRule(
            BearerToken = "Bearer " + TOKEN_API,
            bodyUpdate
        ).enqueue(object :
            Callback<RuleModel> {
            override fun onResponse(call: Call<RuleModel>, response: Response<RuleModel>) {
                if(response.code() == 201){
                    navController.navigate(route = Screens.RulesOwner.route){
                        popUpTo(Screens.RulesOwner.route) {
                            inclusive = true
                        }
                    }
                }
            }

            override fun onFailure(call: Call<RuleModel>, t: Throwable) {
//                Log.d("debug_transaction", t.message.toString())
                if (t.message == t.message){
                    RULE_ERROR_MESSAGE = t.message.toString()
//                    BUTTON_MENU_EDIT = true
                }
            }
        })
    }

    fun deleteRule(
        idRule: String,
        navController: NavController
    ){
        try {
            RuleApp.CreateInstance().deleteRule(
                BearerToken = "Bearer " + TOKEN_API,
                id = idRule
            ).enqueue(object :
                Callback<RuleModel> {
                override fun onResponse(call: Call<RuleModel>, response: Response<RuleModel>) {
//                    Log.d("debug", "Code Delete Menu ${response.code()}")
                    if(response.code() == 200){
                        navController.navigate(route = Screens.RulesOwner.route){
                            popUpTo(Screens.RulesOwner.route) {
                                inclusive = true
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<RuleModel>, t: Throwable) {
                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            RULE_ERROR_MESSAGE = e.message.toString()
            Log.d("debug", "ERROR $RULE_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

}