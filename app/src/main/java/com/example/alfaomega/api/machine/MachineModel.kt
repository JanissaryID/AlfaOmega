package com.example.alfaomega.api.machine

import com.google.gson.annotations.SerializedName

data class MachineModel(

	@field:SerializedName("machine_type")
	val machineType: Boolean? = null,

	@field:SerializedName("transaction_id")
	val transactionId: String? = null,

	@field:SerializedName("machine_status")
	val machineStatus: Boolean? = null,

	@field:SerializedName("interupt")
	val interupt: Boolean? = null,

	@field:SerializedName("is_washer")
	val isWasher: Boolean? = null,

	@field:SerializedName("machine_class")
	val machineClass: Boolean? = null,

	@field:SerializedName("firmware_version")
	val firmwareVersion: String? = null,

	@field:SerializedName("tolerance_time")
	val toleranceTime: Int? = null,

	@field:SerializedName("machine_debug")
	val machineDebug: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("machine_number")
	val machineNumber: Int? = null,

	@field:SerializedName("machine_time")
	val machineTime: Int? = null,

	@field:SerializedName("is_dryer")
	val isDryer: Boolean? = null,

	@field:SerializedName("machine_store")
	val machineStore: String? = null,

	@field:SerializedName("interupt_update")
	val interuptUpdate: Boolean? = null,

	@field:SerializedName("_id")
	val id: String? = null
)
