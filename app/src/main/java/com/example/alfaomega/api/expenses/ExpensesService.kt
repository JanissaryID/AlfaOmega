package com.example.alfaomega.api.expenses

import retrofit2.Call
import retrofit2.http.*

interface ExpensesService {
    @GET("NewExpenses")
    fun fetchExpensesByOwner(
        @Header("Authorization") BearerToken: String,
        @Query(value="date[\$contains]", encoded=true) date: String?,
        @Query(value="owner_id", encoded=true) owner: String?
    ): Call<ArrayList<ExpensesModel>>

    @GET("NewExpenses")
    fun fetchExpensesByStore(
        @Header("Authorization") BearerToken: String,
        @Query(value="date[\$contains]", encoded=true) date: String?,
        @Query(value="storeid", encoded=true) store: String?
    ): Call<ArrayList<ExpensesModel>>

    @GET("NewExpenses")
    fun fetchExpensesByStoreNotNUll(
        @Header("Authorization") BearerToken: String,
        @Query(value="date", encoded=true) date: String?,
        @Query(value="store_id", encoded=true) store: String?
    ): Call<ArrayList<ExpensesModel>>

    @POST("NewExpenses")
    fun insertExpenses(
        @Header("Authorization") BearerToken: String,
        @Body Data: ExpensesModel
    ) : Call<ExpensesModel>

    @PATCH("NewExpenses/{id}")
    fun updateExpenses(
        @Header("Authorization") BearerToken: String,
        @Path("id") id: String?, @Body eData : ExpensesModel
    ): Call<ExpensesModel>
}