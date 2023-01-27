package com.example.alfaomega.api.problem

import retrofit2.Call
import retrofit2.http.*

interface ProblemService {
    @GET("NewMachineProblem")
    fun fetchProblemMachine(
        @Header("Authorization") BearerToken: String,
        @Query(value="id_store", encoded=true) store: String?,
        @Query(value="id_machine", encoded=true) idMachine: String?,
    ): Call<ArrayList<ProblemModel>>

    @POST("NewMachineProblem")
    fun insertProblemMachine(
        @Header("Authorization") BearerToken: String,
        @Body statusData: ProblemModel
    ) : Call<ProblemModel>

    @PATCH("NewMachineProblem/{id}")
    fun updateProblemMachine(
        @Header("Authorization") BearerToken: String,
        @Path("id") id: String?, @Body updateData : ProblemModel
    ): Call<ProblemModel>
}