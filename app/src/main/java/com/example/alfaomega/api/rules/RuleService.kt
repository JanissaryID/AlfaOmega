package com.example.alfaomega.api.rules

import com.example.alfaomega.api.menu.MenuModel
import retrofit2.Call
import retrofit2.http.*

interface RuleService {
    @GET("NewPrinter")
    fun fetchRule(
        @Header("Authorization") BearerToken: String,
        @Query(value="owner_id", encoded=true) OwnerId: String?
    ): Call<ArrayList<RuleModel>>

    @PATCH("NewPrinter/{id}")
    fun updateRule(
        @Header("Authorization") BearerToken: String,
        @Path("id") id: String?, @Body updateData : RuleModel
    ): Call<RuleModel>

    @POST("NewPrinter")
    fun insertRule(
        @Header("Authorization") BearerToken: String,
        @Body statusData: RuleModel
    ) : Call<RuleModel>

    @DELETE("NewPrinter/{id}")
    fun deleteRule(
        @Header("Authorization") BearerToken: String,
        @Path("id") id: String?
    ): Call<RuleModel>
}