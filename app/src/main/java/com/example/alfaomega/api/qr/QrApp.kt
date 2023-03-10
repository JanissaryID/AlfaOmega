package com.example.alfaomega.api.qr

import com.example.alfaomega.KEY_API
import com.example.alfaomega.URL_SERVER
import com.example.alfaomega.api.problem.ProblemService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QrApp {
    private var BASE_URL = "$URL_SERVER/$KEY_API/"

    fun CreateInstance(): QrService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(QrService::class.java)
    }
}