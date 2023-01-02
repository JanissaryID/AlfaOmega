package com.example.alfaomega.api.menu

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.api.machine.MachineApp
import com.example.alfaomega.api.machine.MachineModel
import com.example.alfaomega.api.transaction.TransactionApp
import com.example.alfaomega.api.transaction.TransactionModel
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MenuViewModel: ViewModel() {

    fun getMenu(){
        try {
            MenuApp.CreateInstance().fetchMenu(store = STORE_ID).enqueue(object :
                Callback<ArrayList<MenuModel>> {
                override fun onResponse(call: Call<ArrayList<MenuModel>>, response: Response<ArrayList<MenuModel>>) {
                    MENU_STATE = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            MENU_LIST_GIANT_RESPONSE = response.body()!!.filter { menu -> menu.menuClass == false } as ArrayList<MenuModel>
                            MENU_LIST_TITAN_RESPONSE = response.body()!!.filter { menu -> menu.menuClass == true } as ArrayList<MenuModel>

                            MENU_STATE = 1
//                            Log.i("i`nfo_response", "Data GIANT : ${MENU_LIST_GIANT_RESPONSE}")
                        }
                        if (MENU_LIST_GIANT_RESPONSE.isNullOrEmpty()){
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

    fun getTime(){
        try {
            MenuApp.CreateInstance().fetchMenu(store = STORE_ID).enqueue(object :
                Callback<ArrayList<MenuModel>> {
                override fun onResponse(call: Call<ArrayList<MenuModel>>, response: Response<ArrayList<MenuModel>>) {
                    MENU_STATE = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            var WashTimeGiant: ArrayList<MenuModel> =  response.body()!!.filter { menu -> menu.menuClass == false && menu.isWasher == true && menu.isDryer == false} as ArrayList<MenuModel>
                            var DryTimeGiant: ArrayList<MenuModel> =  response.body()!!.filter { menu -> menu.menuClass == false && menu.isWasher == false && menu.isDryer == true} as ArrayList<MenuModel>
                            var WashTimeTitan: ArrayList<MenuModel> =  response.body()!!.filter { menu -> menu.menuClass == true && menu.isWasher == true && menu.isDryer == false} as ArrayList<MenuModel>
                            var DryTimeTitan: ArrayList<MenuModel> =  response.body()!!.filter { menu -> menu.menuClass == true && menu.isWasher == false && menu.isDryer == true} as ArrayList<MenuModel>

//                            Log.i("info_response", "TIME 2 : ${WashTimeGiant[0].menuTime!!.toInt()}  ${DryTimeGiant[0].menuTime!!.toInt()}  ${WashTimeTitan[0].menuTime!!.toInt()}  ${DryTimeTitan[0].menuTime!!.toInt()}")

//                            TIME_WASHER_GIANT = WashTimeGiant[0].menuTime!!.toInt()
//                            TIME_DRYER_GIANT = DryTimeGiant[0].menuTime!!.toInt()
//                            TIME_WASHER_TITAN = WashTimeTitan[0].menuTime!!.toInt()
//                            TIME_DRYER_TITAN = DryTimeTitan[0].menuTime!!.toInt()

                            MENU_STATE = 1
//                            Log.i("i`nfo_response", "Data GIANT : ${MENU_LIST_GIANT_RESPONSE}")
                        }
                        if (MENU_LIST_GIANT_RESPONSE.isNullOrEmpty()){
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

    fun updateMenu(
        idMenu: String,
        isWasher: Boolean,
        isDryer: Boolean,
        isService: Boolean,
        menuTitle: String,
        menuPrice: String,
        menuClass: Boolean,
        navController: NavController
    ){
        val bodyDataUpdate = MenuModel(
            menuTitle = menuTitle,
            menuClass = menuClass,
            menuTime = 0,
            menuPrice = menuPrice,
            isWasher = isWasher,
            isDryer = isDryer,
            isService = isService
        )

        try {
            MenuApp.CreateInstance().updateMenu(id = idMenu, bodyDataUpdate).enqueue(object :
                Callback<MenuModel> {
                override fun onResponse(call: Call<MenuModel>, response: Response<MenuModel>) {
//                    Log.d("info_update", "Code Update Machine ${response}")
                    if(response.code() == 200){
                        val responseBodyData = response.body()
//                        Log.d("info_update", "Body Update Machine ${response.body()}")
                        if (!responseBodyData!!.id.isNullOrEmpty()){

                            BUTTON_MENU_EDIT = true

                            navController.navigate(route = Screens.MenuOwner.route){
                                popUpTo(Screens.MenuOwner.route) {
                                    inclusive = true
                                }
                            }
                        }
                        else{
                            updateMenu(
                                idMenu = ID_MENU_EDIT,
                                menuTitle = menuTitle,
                                menuPrice = menuPrice,
                                menuClass = menuClass,
                                isWasher = isWasher,
                                isDryer = isDryer,
                                isService = isService,
                                navController = navController
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<MenuModel>, t: Throwable) {
                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            MENU_ERROR_MESSAGE = e.message.toString()
            Log.d("debug", "ERROR $MENU_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertMenu(
        isWasher: Boolean,
        isDryer: Boolean,
        isService: Boolean,
        menuTitle: String,
        menuPrice: String,
        menuStore: String,
        menuClass: Boolean,
        navController: NavController
    ){
        val bodyUpdate = MenuModel(
            isWasher = isWasher,
            isDryer = isDryer,
            isService = isService,
            menuTitle = menuTitle,
            menuPrice = menuPrice,
            menuStore = menuStore,
            menuTime = 0,
            menuClass = menuClass,
        )

        MenuApp.CreateInstance().insertMenu(bodyUpdate).enqueue(object :
            Callback<MenuModel> {
            override fun onResponse(call: Call<MenuModel>, response: Response<MenuModel>) {
//                Log.d("debug", "Code Insert Transaction ${response}")
//                Log.i("info_response", "Response Insert Transaction : ${response}")
                if(response.code() == 201){
                    navController.navigate(route = Screens.MenuOwner.route){
                        popUpTo(Screens.MenuOwner.route) {
                            inclusive = true
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MenuModel>, t: Throwable) {
//                Log.d("debug_transaction", t.message.toString())
                if (t.message == t.message){
                    MENU_ERROR_MESSAGE = t.message.toString()
                    BUTTON_MENU_EDIT = true
                }
            }
        })
    }

    fun deleteMenu(
        idMenu: String,
        navController: NavController
    ){
        try {
            MenuApp.CreateInstance().deleteMenu(id = idMenu).enqueue(object :
                Callback<MenuModel> {
                override fun onResponse(call: Call<MenuModel>, response: Response<MenuModel>) {
//                    Log.d("debug", "Code Delete Menu ${response.code()}")
                    if(response.code() == 200){
                        navController.navigate(route = Screens.MenuOwner.route){
                            popUpTo(Screens.MenuOwner.route) {
                                inclusive = true
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<MenuModel>, t: Throwable) {
                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            MENU_ERROR_MESSAGE = e.message.toString()
            Log.d("debug", "ERROR $MENU_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

}