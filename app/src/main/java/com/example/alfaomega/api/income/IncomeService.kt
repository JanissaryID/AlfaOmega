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

//    @POST("NewIncomeGraph")
//    fun fetchIncomeByOwner(
//        @Header("Authorization") BearerToken: String,
//        @Body statusData: LogModel
//    ) : Call<LogModel>
}