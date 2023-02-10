package com.example.alfaomega.api.income

import retrofit2.Call
import retrofit2.http.*

interface IncomeService {
    @GET("NewIncomeGraph")
    fun fetchIncomeByOwner(
        @Header("Authorization") BearerToken: String,
        @Query(value="date[\$contains]", encoded=true) date: String?,
        @Query(value="owner_id", encoded=true) owner: String?
    ): Call<ArrayList<IncomeModel>>

    @GET("NewIncomeGraph")
    fun fetchIncomeByStore(
        @Header("Authorization") BearerToken: String,
        @Query(value="date[\$contains]", encoded=true) date: String?,
        @Query(value="store_id", encoded=true) store: String?
    ): Call<ArrayList<IncomeModel>>

    @GET("NewIncomeGraph")
    fun fetchIncomeByStoreNotNUll(
        @Header("Authorization") BearerToken: String,
        @Query(value="date", encoded=true) date: String?,
        @Query(value="store_id", encoded=true) store: String?
    ): Call<ArrayList<IncomeModel>>

    @POST("NewIncomeGraph")
    fun insertIncome(
        @Header("Authorization") BearerToken: String,
        @Body Data: IncomeModel
    ) : Call<IncomeModel>

    @PATCH("NewIncomeGraph/{id}")
    fun updateIncome(
        @Header("Authorization") BearerToken: String,
        @Path("id") id: String?, @Body eData : IncomeModel
    ): Call<IncomeModel>
}