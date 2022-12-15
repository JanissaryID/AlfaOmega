package com.example.alfaomega.api.user

import com.example.alfaomega.api.store.StoreModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("NewUser")
    fun getUser(
        @Query(value="username", encoded=true) username: String?,
        @Query(value="password_user", encoded=true) password: String?
    ): Call<ArrayList<UserModel>>

}