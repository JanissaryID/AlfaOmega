package com.example.alfaomega.api.user

import com.example.alfaomega.api.rules.RuleModel
import com.example.alfaomega.api.store.StoreModel
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @GET("NewUser")
    fun getUser(
        @Query(value="username", encoded=true) username: String?,
        @Query(value="password_user", encoded=true) password: String?
    ): Call<ArrayList<UserModel>>

    @GET("NewUser")
    fun fetchUser(): Call<ArrayList<UserModel>>

    @PATCH("NewUser/{id}")
    fun updateUser(
        @Path("id") id: String?, @Body updateData : UserModel
    ): Call<UserModel>

    @POST("NewUser")
    fun insertUser(@Body statusData: UserModel) : Call<UserModel>

    @DELETE("NewUser/{id}")
    fun deleteUser( @Path("id") id: String? ): Call<UserModel>

}