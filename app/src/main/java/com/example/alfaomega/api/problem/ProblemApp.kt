package com.example.alfaomega.api.problem

import com.example.alfaomega.KEY_API
import com.example.alfaomega.api.machine.MachineService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProblemApp {
    private var BASE_URL = "https://api.v2.kontenbase.com/query/api/v1/$KEY_API/"

    fun CreateInstance(): ProblemService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ProblemService::class.java)
    }
}