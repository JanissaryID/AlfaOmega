package com.example.alfaomega.api.store

import com.example.alfaomega.api.menu.MenuModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

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

}