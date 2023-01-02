package com.example.alfaomega.api.store

import com.example.alfaomega.KEY_API
import com.example.alfaomega.api.menu.MenuService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object StoreApp {
    private var BASE_URL = "https://api.v2.kontenbase.com/query/api/v1/$KEY_API/"

    fun CreateInstance(): StoreService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(StoreService::class.java)
    }
}