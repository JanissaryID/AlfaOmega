package com.example.alfaomega.api.rules

import com.google.gson.annotations.SerializedName

data class RuleModel(

	@field:SerializedName("owner_id")
	val ownerId: String? = null,

	@field:SerializedName("rule")
	val rule: String? = null,

	@field:SerializedName("_id")
	val id: String? = null
)
