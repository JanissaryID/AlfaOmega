package com.example.alfaomega.api.log

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LogService {
    @GET("NewLogMachine")
    fun fetchLogMachine(
        @Query(value="machine_store", encoded=true) store: String?,
        @Query(value="date", encoded=true) date: String?
    ): Call<ArrayList<LogModel>>

    @POST("NewLogMachine")
    fun insertLog(@Body statusData: LogModel) : Call<LogModel>
}