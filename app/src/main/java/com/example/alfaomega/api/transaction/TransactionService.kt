package com.example.alfaomega.api.transaction

import com.example.alfaomega.api.machine.MachineModel
import com.example.alfaomega.api.menu.MenuModel
import retrofit2.Call
import retrofit2.http.*

interface TransactionService {
    @GET("NewTransaction")
    fun fetchTransactionActive(
        @Header("Authorization") BearerToken: String,
        @Query(value="transaction_store", encoded=true) store: String?,
    ): Call<ArrayList<TransactionModel>>

    @POST("NewTransaction")
    fun insertTransactions(
        @Header("Authorization") BearerToken: String,
        @Body statusData: TransactionModel
    ) : Call<TransactionModel>

    @PATCH("NewTransaction/{id}")
    fun updateMachine(
        @Header("Authorization") BearerToken: String,
        @Path("id") id: String?, @Body updateData : TransactionModel
    ): Call<TransactionModel>

    @GET("NewTransaction")
    fun fetchTransactionNow(
        @Header("Authorization") BearerToken: String,
        @Query(value="transaction_date", encoded=true) date: String?,
        @Query(value="transaction_store", encoded=true) store: String?,
    ): Call<ArrayList<TransactionModel>>

    @GET("NewTransaction")
    fun fetchTransactionDate(
        @Header("Authorization") BearerToken: String,
        @Query(value="transaction_date", encoded=true) date: String?,
        @Query(value="transaction_store", encoded=true) store: String?,
    ): Call<ArrayList<TransactionModel>>
}