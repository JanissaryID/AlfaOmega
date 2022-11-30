package com.example.alfaomega.api.transaction

import com.example.alfaomega.api.menu.MenuModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TransactionService {
    @GET("NewTransaction")
    fun fetchTransactionActive(
        @Query(value="transaction_store", encoded=true) store: String?,
    ): Call<ArrayList<TransactionModel>>

    @POST("NewTransaction")
    fun insertTransactions(@Body statusData: TransactionModel) : Call<TransactionModel>
}