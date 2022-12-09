package com.example.alfaomega.api.transaction

import com.example.alfaomega.api.machine.MachineModel
import com.example.alfaomega.api.menu.MenuModel
import retrofit2.Call
import retrofit2.http.*

interface TransactionService {
    @GET("NewTransaction")
    fun fetchTransactionActive(
        @Query(value="transaction_store", encoded=true) store: String?,
    ): Call<ArrayList<TransactionModel>>

    @POST("NewTransaction")
    fun insertTransactions(@Body statusData: TransactionModel) : Call<TransactionModel>

    @PATCH("NewTransaction/{id}")
    fun updateMachine(
        @Path("id") id: String?, @Body updateData : TransactionModel
    ): Call<TransactionModel>

    @GET("NewTransaction")
    fun fetchTransactionNow(
        @Query(value="transaction_date", encoded=true) date: String?,
    ): Call<ArrayList<TransactionModel>>
}