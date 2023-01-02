package com.example.alfaomega.api.log

import retrofit2.Call
import retrofit2.http.*

interface LogService {
    @GET("NewLogMachine")
    fun fetchLogMachine(
        @Header("Authorization") BearerToken: String,
        @Query(value="machine_store", encoded=true) store: String?,
        @Query(value="date", encoded=true) date: String?
    ): Call<ArrayList<LogModel>>

    @POST("NewLogMachine")
    fun insertLog(
        @Header("Authorization") BearerToken: String,
        @Body statusData: LogModel
    ) : Call<LogModel>
}