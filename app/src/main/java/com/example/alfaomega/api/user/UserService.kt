package com.example.alfaomega.api.user

import com.example.alfaomega.api.rules.RuleModel
import com.example.alfaomega.api.store.StoreModel
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @GET("NewUser")
    fun getUser(
        @Header("Authorization") BearerToken: String,
        @Query(value="username", encoded=true) username: String?,
        @Query(value="password_user", encoded=true) password: String?,
//        @Query(value="statususer", encoded=true) status: Boolean?
    ): Call<ArrayList<UserModel>>

    @GET("NewUser")
    fun fetchUser(
        @Header("Authorization") BearerToken: String,
        @Query(value="id_owner", encoded=true) OwnerId: String?
    ): Call<ArrayList<UserModel>>

    @GET("NewUser")
    fun fetcOwner(
        @Header("Authorization") BearerToken: String,
    ): Call<ArrayList<UserModel>>

    @PATCH("NewUser/{id}")
    fun updateUser(
        @Header("Authorization") BearerToken: String,
        @Path("id") id: String?, @Body updateData : UserModel
    ): Call<UserModel>

    @POST("NewUser")
    fun insertUser(
        @Header("Authorization") BearerToken: String,
        @Body statusData: UserModel
    ) : Call<UserModel>

    @DELETE("NewUser/{id}")
    fun deleteUser(
        @Header("Authorization") BearerToken: String,
        @Path("id") id: String?
    ): Call<UserModel>

}