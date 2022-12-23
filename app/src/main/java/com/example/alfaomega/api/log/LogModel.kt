package com.example.alfaomega.api.log

import com.google.gson.annotations.SerializedName

data class LogModel(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("machine_type")
	val machineType: Boolean? = null,

	@field:SerializedName("machine_status")
	val machineStatus: Boolean? = null,

	@field:SerializedName("log")
	val log: String? = null,

	@field:SerializedName("machine_number")
	val machineNumber: Int? = null,

	@field:SerializedName("machine_store")
	val machineStore: String? = null,

	@field:SerializedName("machine_class")
	val machineClass: Boolean? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("device")
	val device: Boolean? = null,

	@field:SerializedName("code_response")
	val codeResponse: String? = null
)
