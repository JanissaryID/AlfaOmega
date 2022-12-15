package com.example.alfaomega.api.user

import com.example.alfaomega.api.store.StoreService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserApp {
    private var BASE_URL = "https://api.v2.kontenbase.com/query/api/v1/d11e834d-5663-4415-9bee-cfb371e77a2e/"

    fun CreateInstance(): UserService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(UserService::class.java)
    }
}