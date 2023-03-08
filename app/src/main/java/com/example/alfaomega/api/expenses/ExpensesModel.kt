package com.example.alfaomega.api.expenses

import com.google.gson.annotations.SerializedName

data class ExpensesModel(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("notes")
	val notes: String? = null,

	@field:SerializedName("admin")
	val admin: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("ownerid")
	val ownerid: String? = null,

	@field:SerializedName("storeid")
	val storeid: String? = null,

	@field:SerializedName("expenses")
	val expenses: String? = null
)
