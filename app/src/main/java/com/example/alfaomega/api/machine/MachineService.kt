package com.example.alfaomega.api.machine

import com.example.alfaomega.api.menu.MenuModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MachineService {
    @GET("NewMachine")
    fun fetchMachine(
        @Query(value="machine_store", encoded=true) store: String?
    ): Call<ArrayList<MachineModel>>
}