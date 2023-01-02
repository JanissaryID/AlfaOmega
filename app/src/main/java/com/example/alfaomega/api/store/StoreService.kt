package com.example.alfaomega.api.store

import com.example.alfaomega.api.menu.MenuModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StoreService {

    @GET("NewStore")
    fun fetchStore(
        @Query(value="owner_id", encoded=true) OwnerId: String?
    ): Call<ArrayList<StoreModel>>

    @GET("NewStore")
    fun getStore(
        @Query(value="_id", encoded=true) id: String?
    ): Call<ArrayList<StoreModel>>

}