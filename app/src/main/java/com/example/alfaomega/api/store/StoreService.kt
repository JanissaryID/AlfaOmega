package com.example.alfaomega.api.store

import com.example.alfaomega.api.menu.MenuModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StoreService {

    @GET("NewStore")
    fun fetchStore(): Call<ArrayList<StoreModel>>

}