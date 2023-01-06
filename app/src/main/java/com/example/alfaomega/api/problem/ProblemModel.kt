package com.example.alfaomega.api.problem

import com.google.gson.annotations.SerializedName

data class ProblemModel(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("problem")
	val problem: String? = null,

	@field:SerializedName("machine_number")
	val machineNumber: Int? = null,

	@field:SerializedName("admin")
	val admin: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("id_store")
	val idStore: String? = null,

	@field:SerializedName("is_solved")
	val isSolved: Boolean? = null
)
