package com.example.alfaomega.api.user

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.alfaomega.*
import com.example.alfaomega.api.machine.MachineModel
import com.example.alfaomega.api.rules.RuleApp
import com.example.alfaomega.api.rules.RuleModel
import com.example.alfaomega.api.store.StoreApp
import com.example.alfaomega.api.store.StoreModel
import com.example.alfaomega.navigations.Screens
import com.example.alfaomega.proto.ProtoViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    fun GetUser(
        username: String,
        password: String,
        protoViewModel: ProtoViewModel,
        navController: NavController
    ){
        var userListResponse:ArrayList<UserModel> by mutableStateOf(arrayListOf())

        val selectionUser = listOf("Owner", "Developer")

        val screenBack = Screens.Home.route

        try {
            UserApp.CreateInstance().getUser(
                BearerToken = "Bearer " + TOKEN_API,
                username = username,
                password = password,
                status = false
            ).enqueue(object :
                Callback<ArrayList<UserModel>> {
                override fun onResponse(call: Call<ArrayList<UserModel>>, response: Response<ArrayList<UserModel>>) {
//                    Log.d("debug_user", "get error = $response")
                    USER_STATE = 0

                    if(response.code() == 200){
                        response.body()?.let {

                            USER_STATE = 1
                            userListResponse = response.body()!!
                            if (userListResponse.isNullOrEmpty()){
                                USER_STATE = 3
                                BUTTON_LOGIN_CLICKED = false
                                FAILED_LOGIN = true
                            }
                            else{
                                if(userListResponse[0].typeUser == 1 && !userListResponse[0].statususer!!){
                                    USER_NAME = selectionUser[0]
                                    USER_TYPE = userListResponse[0].typeUser!!
                                    OWNER_ID = userListResponse[0].id!!
                                    protoViewModel.updateNameUser(Nameuser = USER_NAME)
                                    protoViewModel.updateTypeUser(TypeUser = USER_TYPE)
                                    protoViewModel.updateOwnerId(OwnerId = OWNER_ID)
                                    updateStatUser(OWNER_ID, true)
                                }
                                else if(userListResponse[0].typeUser == 3 && !userListResponse[0].statususer!!){
                                    USER_NAME = userListResponse[0].username!!
                                    USER_TYPE = userListResponse[0].typeUser!!
                                    OWNER_ID = userListResponse[0].idOwner!!
                                    USER_ID = userListResponse[0].id!!
                                    protoViewModel.updateNameUser(Nameuser = USER_NAME)
                                    protoViewModel.updateTypeUser(TypeUser = USER_TYPE)
                                    protoViewModel.updateOwnerId(OwnerId = OWNER_ID)
                                    updateStatUser(USER_ID, true)
                                }
                                else{
                                    USER_NAME = selectionUser[1]
                                    USER_TYPE = userListResponse[0].typeUser!!
                                    OWNER_ID = userListResponse[0].idOwner!!
                                    USER_ID = userListResponse[0].id!!
                                    protoViewModel.updateNameUser(Nameuser = USER_NAME)
                                    protoViewModel.updateTypeUser(TypeUser = USER_TYPE)
                                    protoViewModel.updateOwnerId(OwnerId = OWNER_ID)
                                    updateStatUser(USER_ID, true)
                                }
                                FAILED_LOGIN = false
                                navController.navigate(route = screenBack) {
                                    popUpTo(screenBack) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }
                    else{
                        BUTTON_LOGIN_CLICKED = false
                        FAILED_LOGIN = true
                    }
                }

                override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {
                    Log.d("debug_user", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug_user", "Failed")
                        USER_STATE = 2
                        BUTTON_LOGIN_CLICKED = false
                        FAILED_LOGIN = true
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            USER_ERROR_MESSAGE = e.message.toString()
            Log.d("debug_user", "ERROR $USER_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    fun fetchUser(){
        try {
            UserApp.CreateInstance().fetchUser(
                BearerToken = "Bearer " + TOKEN_API,
                OwnerId = OWNER_ID
            ).enqueue(object :
                Callback<ArrayList<UserModel>> {
                override fun onResponse(call: Call<ArrayList<UserModel>>, response: Response<ArrayList<UserModel>>) {
                    USER_STATE = 0

                    LIST_USER.clear()

                    if(response.code() == 200){
                        response.body()?.let {
                            LIST_USER = response.body()!!.filter { user -> user.typeUser == 3} as ArrayList<UserModel>
                            Log.i("info_response", "User : ${LIST_USER}")
                            USER_STATE = 1
                        }
                        if (LIST_USER.isNullOrEmpty()){
                            USER_STATE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {
                    if (t.message == t.message){
                        USER_STATE = 2
                    }
                }
            })
        }
        catch (e : Exception){
            USER_ERROR_MESSAGE = e.message.toString()
            Log.d("debug menu", "ERROR $USER_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    fun fetchOwner(){
        try {
            UserApp.CreateInstance().fetcOwner(
                BearerToken = "Bearer " + TOKEN_API,
            ).enqueue(object :
                Callback<ArrayList<UserModel>> {
                override fun onResponse(call: Call<ArrayList<UserModel>>, response: Response<ArrayList<UserModel>>) {
                    USER_STATE = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            LIST_OWNER = response.body()!!.filter { user -> user.typeUser == 1} as ArrayList<UserModel>
//                            Log.i("info_response", "User : ${LIST_OWNER}")
                            USER_STATE = 1
                        }
                        if (LIST_OWNER.isNullOrEmpty()){
                            USER_STATE = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {
                    if (t.message == t.message){
                        USER_STATE = 2
                    }
                }
            })
        }
        catch (e : Exception){
            USER_ERROR_MESSAGE = e.message.toString()
            Log.d("debug menu", "ERROR $USER_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    fun updateStatUser(
        idUser: String,
        statUser: Boolean
    ){
        val bodyDataUpdate = UserModel(
            statususer = statUser,
        )

        try {
            UserApp.CreateInstance().updateUser(
                BearerToken = "Bearer " + TOKEN_API,
                id = idUser,
                bodyDataUpdate).enqueue(object :
                Callback<UserModel> {
                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    if(response.code() == 200){
                        val responseBodyData = response.body()
                        if (responseBodyData!!.id.isNullOrEmpty()){
                            updateStatUser(
                                idUser = idUser,
                                statUser = statUser
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            USER_ERROR_MESSAGE = e.message.toString()
            Log.d("debug", "ERROR $USER_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    fun updateUser(
        idUser: String,
        nameUser: String,
        passwordUser: String,
        ScreenDestination: String,
        navController: NavController
    ){
        val bodyDataUpdate = UserModel(
            username = nameUser,
            passwordUser = passwordUser
        )

        try {
            UserApp.CreateInstance().updateUser(
                BearerToken = "Bearer " + TOKEN_API,
                id = idUser,
                bodyDataUpdate).enqueue(object :
                Callback<UserModel> {
                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
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
                            updateUser(
                                idUser = idUser,
                                nameUser = nameUser,
                                passwordUser = passwordUser,
                                ScreenDestination = ScreenDestination,
                                navController = navController
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            USER_ERROR_MESSAGE = e.message.toString()
            Log.d("debug", "ERROR $USER_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun insertUser(
        username: String,
        passwordUser: String,
        idOwner: String,
        typeUser: Int,
        ScreenDestination: String,
        navController: NavController
    ){
        val bodyUpdate = UserModel(
            username = username,
            passwordUser = passwordUser,
            typeUser = typeUser,
            idOwner = idOwner
        )

        UserApp.CreateInstance().insertUser(
            BearerToken = "Bearer " + TOKEN_API,
            bodyUpdate).enqueue(object :
            Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
//                Log.d("debug_user", response.toString())
                if(response.code() == 201){
                    navController.navigate(route = ScreenDestination){
                        popUpTo(ScreenDestination) {
                            inclusive = true
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
//                Log.d("debug_transaction", t.message.toString())
                if (t.message == t.message){
                    USER_ERROR_MESSAGE = t.message.toString()
//                    BUTTON_MENU_EDIT = true
                }
            }
        })
    }

    fun deleteUser(
        iduser: String,
        navController: NavController
    ){
        try {
            UserApp.CreateInstance().deleteUser(
                BearerToken = "Bearer " + TOKEN_API,
                id = iduser
            ).enqueue(object :
                Callback<UserModel> {
                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
//                    Log.d("debug", "Code Delete Menu ${response.code()}")
                    if(response.code() == 200){
                        if(USER_TYPE == 2){
                            navController.navigate(route = Screens.OwnerListDeveloper.route){
                                popUpTo(Screens.OwnerListDeveloper.route) {
                                    inclusive = true
                                }
                            }
                        }
                        else{
                            navController.navigate(route = Screens.UserOwner.route){
                                popUpTo(Screens.UserOwner.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            USER_ERROR_MESSAGE = e.message.toString()
            Log.d("debug", "ERROR $USER_ERROR_MESSAGE")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

}