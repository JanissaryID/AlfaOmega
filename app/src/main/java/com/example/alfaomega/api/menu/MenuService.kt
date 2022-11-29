package com.example.alfaomega.api.menu

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MenuService {
    @GET("NewMenu")
    fun fetchMenu(
        @Query(value="menu_store", encoded=true) store: String?,
        @Query(value="menu_class", encoded=true) menuClass: Boolean?
    ): Call<ArrayList<MenuModel>>
}