package com.example.alfaomega.api.rules

import com.example.alfaomega.api.menu.MenuService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RuleApp {
    private var BASE_URL = "https://api.v2.kontenbase.com/query/api/v1/d11e834d-5663-4415-9bee-cfb371e77a2e/"

    fun CreateInstance(): RuleService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RuleService::class.java)
    }
}