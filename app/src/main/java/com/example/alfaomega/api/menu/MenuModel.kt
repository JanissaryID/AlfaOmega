package com.example.alfaomega.api.menu

import com.google.gson.annotations.SerializedName

data class MenuModel(

	@field:SerializedName("is_service")
	val isService: Boolean? = null,

	@field:SerializedName("menu_price")
	val menuPrice: String? = null,

	@field:SerializedName("menu_time")
	val menuTime: Int? = null,

	@field:SerializedName("is_washer")
	val isWasher: Boolean? = null,

	@field:SerializedName("menu_store")
	val menuStore: String? = null,

	@field:SerializedName("menu_title")
	val menuTitle: String? = null,

	@field:SerializedName("is_dryer")
	val isDryer: Boolean? = null,

	@field:SerializedName("menu_class")
	val menuClass: Boolean? = null,

	@field:SerializedName("_id")
	val id: String? = null
)
