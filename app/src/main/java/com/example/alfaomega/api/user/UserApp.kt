package com.example.alfaomega.api.user

import com.example.alfaomega.KEY_API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserApp {
    private var BASE_URL = "https://api.v2.kontenbase.com/query/api/v1/$KEY_API/"

    fun CreateInstance(): UserService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(UserService::class.java)
    }
}