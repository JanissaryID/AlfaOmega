package com.example.alfaomega.api.expenses

import com.example.alfaomega.KEY_API
import com.example.alfaomega.URL_SERVER
import com.example.alfaomega.api.income.IncomeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ExpensesApp {
    private var BASE_URL = "$URL_SERVER/$KEY_API/"

    fun CreateInstance(): ExpensesService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ExpensesService::class.java)
    }
}