package com.example.alfaomega.api.rules

import com.example.alfaomega.api.menu.MenuModel
import retrofit2.Call
import retrofit2.http.*

interface RuleService {
    @GET("NewPrinter")
    fun fetchRule(): Call<ArrayList<RuleModel>>

    @PATCH("NewPrinter/{id}")
    fun updateRule(
        @Path("id") id: String?, @Body updateData : RuleModel
    ): Call<RuleModel>

    @POST("NewPrinter")
    fun insertRule(@Body statusData: RuleModel) : Call<RuleModel>

    @DELETE("NewPrinter/{id}")
    fun deleteRule( @Path("id") id: String? ): Call<RuleModel>
}