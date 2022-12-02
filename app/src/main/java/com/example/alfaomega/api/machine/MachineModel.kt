package com.example.alfaomega.api.machine

import com.google.gson.annotations.SerializedName

data class MachineModel(

	@field:SerializedName("machine_type")
	val machineType: Any? = null,

	@field:SerializedName("transaction_id")
	val transactionId: Any? = null,

	@field:SerializedName("machine_status")
	val machineStatus: Any? = null,

	@field:SerializedName("interupt")
	val interupt: Any? = null,

	@field:SerializedName("machine_number")
	val machineNumber: Int? = null,

	@field:SerializedName("machine_time")
	val machineTime: Int? = null,

	@field:SerializedName("machine_store")
	val machineStore: String? = null,

	@field:SerializedName("interupt_update")
	val interuptUpdate: Any? = null,

	@field:SerializedName("machine_class")
	val machineClass: Boolean? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("firmware_version")
	val firmwareVersion: Any? = null,

	@field:SerializedName("machine_debug")
	val machineDebug: Int? = null
)
