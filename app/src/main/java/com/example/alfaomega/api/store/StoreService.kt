package com.example.alfaomega.api.store

import com.example.alfaomega.api.menu.MenuModel
import com.example.alfaomega.api.user.UserModel
import retrofit2.Call
import retrofit2.http.*

interface StoreService {

    @GET("NewStore")
    fun fetchStore(
        @Header("Authorization") BearerToken: String,
        @Query(value="owner_id", encoded=true) OwnerId: String?
    ): Call<ArrayList<StoreModel>>

    @GET("NewStore")
    fun getStore(
        @Header("Authorization") BearerToken: String,
        @Query(value="_id", encoded=true) id: String?
    ): Call<ArrayList<StoreModel>>

    @PATCH("NewStore/{id}")
    fun updateStore(
        @Header("Authorization") BearerToken: String,
        @Path("id") id: String?, @Body updateData : StoreModel
    ): Call<StoreModel>

    @POST("NewStore")
    fun insertStore(
        @Header("Authorization") BearerToken: String,
        @Body statusData: StoreModel
    ) : Call<StoreModel>

    @DELETE("NewStore/{id}")
    fun deleteStore(
        @Header("Authorization") BearerToken: String,
        @Path("id") id: String?
    ): Call<StoreModel>

}