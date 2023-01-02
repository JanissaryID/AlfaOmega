package com.example.alfaomega.api.rules

import com.example.alfaomega.KEY_API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RuleApp {
    private var BASE_URL = "https://api.v2.kontenbase.com/query/api/v1/$KEY_API/"

    fun CreateInstance(): RuleService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RuleService::class.java)
    }
}