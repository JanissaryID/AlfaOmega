package com.example.alfaomega.api.qr

import com.example.alfaomega.api.problem.ProblemModel
import retrofit2.Call
import retrofit2.http.*

interface QrService {

    @GET("QrOwner")
    fun fetchQr(
        @Header("Authorization") BearerToken: String,
        @Query(value="ownerid", encoded=true) owner: String?,
    ): Call<ArrayList<QrModel>>

    @POST("QrOwner")
    fun insertQr(
        @Header("Authorization") BearerToken: String,
        @Body statusData: QrModel
    ) : Call<QrModel>

    @PATCH("QrOwner/{id}")
    fun updateQr(
        @Header("Authorization") BearerToken: String,
        @Path("id") id: String?, @Body updateData : QrModel
    ): Call<QrModel>

}