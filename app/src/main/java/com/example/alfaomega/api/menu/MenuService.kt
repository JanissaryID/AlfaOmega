package com.example.alfaomega.api.menu

import com.example.alfaomega.api.machine.MachineModel
import com.example.alfaomega.api.transaction.TransactionModel
import retrofit2.Call
import retrofit2.http.*

interface MenuService {
    @GET("NewMenu")
    fun fetchMenu(
        @Header("Authorization") BearerToken: String,
        @Query(value="menu_store", encoded=true) store: String?
    ): Call<ArrayList<MenuModel>>

    @PATCH("NewMenu/{id}")
    fun updateMenu(
        @Header("Authorization") BearerToken: String,
        @Path("id") id: String?, @Body updateData : MenuModel
    ): Call<MenuModel>

    @POST("NewMenu")
    fun insertMenu(
        @Header("Authorization") BearerToken: String,
        @Body statusData: MenuModel
    ) : Call<MenuModel>

    @DELETE("NewMenu/{id}")
    fun deleteMenu(
        @Header("Authorization") BearerToken: String,
        @Path("id") id: String?
    ): Call<MenuModel>
}