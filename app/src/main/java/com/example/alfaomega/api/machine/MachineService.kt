package com.example.alfaomega.api.machine

import com.example.alfaomega.api.menu.MenuModel
import retrofit2.Call
import retrofit2.http.*

interface MachineService {
    @GET("NewMachine")
    fun fetchMachine(
        @Query(value="machine_store", encoded=true) store: String?
    ): Call<ArrayList<MachineModel>>

    @PATCH("NewMachine/{id}")
    fun updateMachine(
        @Path("id") id: String?, @Body updateData : MachineModel
    ): Call<MachineModel>
}