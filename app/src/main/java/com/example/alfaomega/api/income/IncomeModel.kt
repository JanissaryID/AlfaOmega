package com.example.alfaomega.api.income

import com.google.gson.annotations.SerializedName

data class IncomeModel(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("income")
	val income: String? = null,

	@field:SerializedName("store_id")
	val storeId: String? = null,

	@field:SerializedName("owner_id")
	val ownerId: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("outcome")
	val outcome: String? = null
)
