package com.example.alfaomega.api.menu

import com.example.alfaomega.KEY_API
import com.example.alfaomega.URL_SERVER
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MenuApp {
    private var BASE_URL = "$URL_SERVER/$KEY_API/"
    fun CreateInstance(): MenuService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(MenuService::class.java)
    }
}